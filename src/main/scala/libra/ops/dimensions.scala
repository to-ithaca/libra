package libra
package ops

import shapeless._
import shapeless.ops.hlist._
import singleton.ops._
import singleton.ops.impl._

object dimensions {

  /** Type class for multiplying two hlists of dimensions */
  trait Multiply[L <: HList, R <: HList] {
    type Out <: HList
  }

  object Multiply {
    type Aux[L <: HList, R <: HList, Out0 <: HList] = Multiply[L, R] { type Out = Out0 }

    implicit def dimensionsMultiplyBase[R <: HList]: Aux[HNil, R, R] = new Multiply[HNil, R] {
      type Out = R
    }

    implicit def dimensionsMultiplyRecurseValid[D, R <: HList, LF <: Fraction[_, _], RF <: Fraction[_, _], TF <: Fraction[_, _], RT <: HList, LT <: HList, T <: HList](
      implicit ev: shapeless.ops.record.Selector.Aux[R, D, RF],
      ev1: fraction.Add.Aux[LF, RF, TF],
      ev2: fraction.Valid[TF],
      ev3: FilterNot.Aux[R, Term[D, RF], RT],
      ev4: Aux[LT, RT, T]
 ): Aux[Term[D, LF] :: LT, R, Term[D, TF] :: T] = new Multiply[Term[D, LF] :: LT, R] {
      type Out = Term[D, TF] :: T
    }

    implicit def dimensionsMultiplyRecurseInvalid[D, R <: HList, LF <: Fraction[_, _], RF <: Fraction[_, _], TF <: Fraction[_, _], RT <: HList, LT <: HList, T <: HList](
      implicit ev: shapeless.ops.record.Selector.Aux[R, D, RF],
      ev1: fraction.Add.Aux[LF, RF, TF],
      ev2: Refute[fraction.Valid[TF]],
      ev3: FilterNot.Aux[R, Term[D, RF], RT],
      ev4: Aux[LT, RT, T]
 ): Aux[Term[D, LF] :: LT, R, T] = new Multiply[Term[D, LF] :: LT, R] {
      type Out = T
    }

    implicit def dimensionsMultiplyRecurseLeft[D, R <: HList, LF <: Fraction[_, _], RF <: Fraction[_, _], TF <: Fraction[_, _], RT <: HList, LT <: HList, T <: HList](
      implicit ev: Refute[shapeless.ops.record.Selector[R, D]],
      ev1: Aux[LT, R, T]
    ): Aux[Term[D, LF] :: LT, R, Term[D, TF] :: T] = new Multiply[Term[D, LF] :: LT, R] {
      type Out = Term[D, TF] :: T
    }
  }

  /** Type class for inverting a hlist of dimensions */
  trait Invert[H <: HList] {
    type Out <: HList
  }

  object Invert {
    type Aux[H <: HList, Out0 <: HList] = Invert[H] { type Out = Out0 }

    implicit def invertBase: Aux[HNil, HNil] = new Invert[HNil] { 
      type Out = HNil
    }
    implicit def invertRecurse[D, F <: Fraction[_, _], NF <: Fraction[_, _], T <: HList, IT <: HList](
      implicit ev0: fraction.Negate.Aux[F, NF],
      ev1: Aux[T, IT]): Aux[Term[D, F] :: T, Term[D, NF] :: IT] = new Invert[Term[D, F] :: T] {
      type Out = Term[D, NF] :: IT
    }
  }

  /** Type class for dividing dimensions `L` by `R` */
  trait Divide[L <: HList, R <: HList] {
    type Out <: HList
  }

  object Divide {
    type Aux[L <: HList, R <: HList, Out0 <: HList] = Divide[L, R] { type Out = Out0 }

    implicit def dimensionsDivide[L <: HList, R <: HList, IR <: HList, M <: HList](
      implicit ev0: Invert.Aux[R, IR],
      ev1: Multiply.Aux[L, IR, M]
    ): Aux[L, R, M] = new Divide[L, R] {
      type Out = M
    }
  }

  /** Type class for raising dimensions to the power `P` */
  trait Power[P <: Singleton with Int, H <: HList] {
    type Out <: HList
  }

  object Power {
    type Aux[P <: Singleton with Int, H <: HList, Out0 <: HList] = Power[P, H] { type Out = Out0 }

    implicit def dimensionsPowerBase[P <: Singleton with Int]: Aux[P, HNil, HNil] = new Power[P, HNil] {
      type Out = HNil
    }

    implicit def dimensionsPowerRecurseValid[P <: Singleton with Int, D, F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList, Out0 <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[P, 1], PF],
      ev1: Aux[P, T, TOut],
      ev2: fraction.Valid[PF]
 ): Aux[P, Term[D, F] :: T, Term[D, PF] :: TOut] = new Power[P, Term[D, F] :: T] {
      type Out = Term[D, PF] :: TOut
    }

    implicit def dimensionsPowerRecurseInvalid[P <: Singleton with Int, D, F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList, Out0 <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[P, 1], PF],
      ev1: Aux[P, T, TOut],
      ev2: Refute[fraction.Valid[PF]]
 ): Aux[P, Term[D, F] :: T, TOut] = new Power[P, Term[D, F] :: T] {
      type Out = TOut
    }
  }


  /** Type class for raising dimensions to the power `1 / P` */
  trait Root[P <: Singleton with Int, H <: HList] {
    type Out <: HList
  }
  object Root {
    type Aux[P <: Singleton with Int, H <: HList, Out0 <: HList] = Root[P, H] { type Out = Out0 }

    implicit def dimensionsRootBase[P <: Singleton with Int]: Aux[P, HNil, HNil] = new Root[P, HNil] {
      type Out = HNil
    }

    implicit def dimensionsRootRecurse[P <: Singleton with Int, D, F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[1, P], PF],
      ev1: Aux[P, T, TOut],
      ev2: fraction.Valid[PF]
    ): Aux[P, Term[D, F] :: T, Term[D, PF] :: TOut] = new Root[P, Term[D, F] :: T] {
      type Out = Term[D, PF] :: TOut
    }

    implicit def dimensionsRootRecurseInvalid[P <: Singleton with Int, D, F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[1, P], PF],
      ev1: Aux[P, T, TOut],
      ev2: Refute[fraction.Valid[PF]]
    ): Aux[P, Term[D, F] :: T, TOut] = new Root[P, Term[D, F] :: T] {
      type Out = TOut
    }
  }

  /** Type class for printing dimensions */
  trait ShowDimension[H <: HList] {
    def apply(): String
  }

  object ShowDimension {
    implicit def dimensionsShowDimensionBase: ShowDimension[HNil] = new ShowDimension[HNil] {
      def apply(): String = ""
    }

    implicit def dimensionsShowDimensionRecurse[D, FN <: Singleton with Int, FD <: Singleton with Int, T <: HList](
      implicit showDimension: base.ShowDimension[D], showTail: ShowDimension[T],
      ev0: Require[FD != 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowDimension[Term[D, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showDimension()}^${numerator.value.toString}/${denominator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowDimensionRecurse1[D, FN <: Singleton with Int, FD <: Singleton with Int, T <: HList](
      implicit showDimension: base.ShowDimension[D], showTail: ShowDimension[T],
      ev0: Require[FD == 1], ev1: LowPriority,
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowDimension[Term[D, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showDimension()}^${numerator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowDimensionRecurse2[D, FN <: Singleton with Int, FD <: Singleton with Int, T <: HList](
      implicit showDimension: base.ShowDimension[D], showTail: ShowDimension[T],
      ev0: Require[FD == 1], ev1: Require[FN == 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowDimension[Term[D, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showDimension()} ${showTail()}"
      }
  }

  /** Type class for printing dimensions */
  trait ShowUnit[H <: HList] {
    def apply(): String
  }


  object ShowUnit {
    implicit def dimensionsShowUnitBase: ShowUnit[HNil] = new ShowUnit[HNil] {
      def apply(): String = ""
    }

    implicit def dimensionsShowUnitRecurse[D, FN <: Singleton with Int, FD <: Singleton with Int, T <: HList](
      implicit showUnit: base.ShowUnit[D], showTail: ShowUnit[T],
      ev0: Require[FD != 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowUnit[Term[D, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showUnit()}^${numerator.value.toString}/${denominator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowUnitRecurse1[D, FN <: Singleton with Int, FD <: Singleton with Int, T <: HList](
      implicit showUnit: base.ShowUnit[D], showTail: ShowUnit[T],
      ev0: Require[FD == 1], ev1: LowPriority,
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowUnit[Term[D, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showUnit()}^${numerator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowUnitRecurse2[D, FN <: Singleton with Int, FD <: Singleton with Int, T <: HList](
      implicit showUnit: base.ShowUnit[D], showTail: ShowUnit[T],
      ev0: Require[FD == 1], ev1: Require[FN == 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowUnit[Term[D, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showUnit()} ${showTail()}"
      }
  }
}

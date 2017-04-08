package libra
package ops

import shapeless._
import shapeless.labelled.FieldType
import shapeless.ops.hlist._
import singleton.ops._
import singleton.ops.impl._
import spire.algebra._, spire.implicits._

object dimensions {

  /** Typeclass for simplifying a merged HList to produce a multiplied HList
    *
    * Given a FieldType[D, (U, F)], produces a FieldType[D, (U, F)] with no change.  This occurs when a term exists in L but not in R, or vice versa
    * Given a FieldType[D, ((U, LF), (U, RF))] produces a FieldType[D, (U, TF)] where TF is the sum of LF and RF.  If TF is invalid, the term is removed from the output.
    */
  trait MultiplyMerged[H <: HList] {
    type Out
  }

  object MultiplyMerged {
    type Aux[H <: HList, Out0 <: HList] = MultiplyMerged[H] { type Out = Out0 }

    implicit def multiplyMergedBase: Aux[HNil, HNil] = new MultiplyMerged[HNil] { type Out = HNil }

    implicit def multiplyMergedRecurseValid[D, U <: Unit[_], LF <: Fraction[_, _], RF <: Fraction[_, _], TF <: Fraction[_, _],
      Tail <: HList, OutTail <: HList](
      implicit ev0: fraction.Add.Aux[LF, RF, TF],
      ev1: fraction.Valid[TF],
      ev2: Aux[Tail, OutTail]
    ): Aux[FieldType[D, ((U, LF), (U, RF))] :: Tail, FieldType[D, (U, TF)] :: OutTail] =
      new MultiplyMerged[FieldType[D, ((U, LF), (U, RF))] :: Tail] {
        type Out = FieldType[D, (U, TF)] :: OutTail
      }


    implicit def multiplyMergedRecurseInvalid[D, U <: Unit[_], LF <: Fraction[_, _], RF <: Fraction[_, _], TF <: Fraction[_, _],
      Tail <: HList, OutTail <: HList](
      implicit ev0: fraction.Add.Aux[LF, RF, TF],
      ev1: Refute[fraction.Valid[TF]],
      ev2: Aux[Tail, OutTail]
    ): Aux[FieldType[D, ((U, LF), (U, RF))] :: Tail, OutTail] =
      new MultiplyMerged[FieldType[D, ((U, LF), (U, RF))] :: Tail] {
        type Out = OutTail
      }

    implicit def multiplyMergedRecurseSingle[D, U <: Unit[_], F <: Fraction[_, _], Tail <: HList, OutTail <: HList](
      implicit ev: Aux[Tail, OutTail]
    ): Aux[FieldType[D, (U, F)] :: Tail, FieldType[D, (U, F)] :: OutTail] = 
      new MultiplyMerged[FieldType[D, (U, F)] :: Tail] {
        type Out = FieldType[D, (U, F)] :: OutTail
      }
  }

  /** Type class for multiplying two hlists of dimensions */
  trait Multiply[L <: HList, R <: HList] {
    type Out <: HList
  }

  object Multiply {
    type Aux[L <: HList, R <: HList, Out0 <: HList] = Multiply[L, R] { type Out = Out0 }

    implicit def dimensionsMultiply[L <: HList, R <: HList, Merged <: HList, Out0 <: HList](
      implicit ev0: Merge.Aux[L, R, Merged],
      ev1: MultiplyMerged.Aux[Merged, Out0]
    ): Aux[L, R, Out0] = new Multiply[L, R] {
      type Out = Out0
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
    implicit def invertRecurse[D, U <: Unit[_], F <: Fraction[_, _], NF <: Fraction[_, _], T <: HList, IT <: HList](
      implicit ev0: fraction.Negate.Aux[F, NF],
      ev1: Aux[T, IT]): Aux[Term[D, U, F] :: T, Term[D, U, NF] :: IT] = new Invert[Term[D, U, F] :: T] {
      type Out = Term[D, U, NF] :: IT
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
  trait Power[P <: XInt, H <: HList] {
    type Out <: HList
  }

  object Power {
    type Aux[P <: XInt, H <: HList, Out0 <: HList] = Power[P, H] { type Out = Out0 }

    implicit def dimensionsPowerBase[P <: XInt]: Aux[P, HNil, HNil] = new Power[P, HNil] {
      type Out = HNil
    }

    implicit def dimensionsPowerRecurseValid[P <: XInt, D, U <: Unit[_], F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList, Out0 <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[P, 1], PF],
      ev1: Aux[P, T, TOut],
      ev2: fraction.Valid[PF]
 ): Aux[P, Term[D, U, F] :: T, Term[D, U, PF] :: TOut] = new Power[P, Term[D, U, F] :: T] {
      type Out = Term[D, U, PF] :: TOut
    }

    implicit def dimensionsPowerRecurseInvalid[P <: XInt, D, U <: Unit[_], F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList, Out0 <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[P, 1], PF],
      ev1: Aux[P, T, TOut],
      ev2: Refute[fraction.Valid[PF]]
 ): Aux[P, Term[D, U, F] :: T, TOut] = new Power[P, Term[D, U, F] :: T] {
      type Out = TOut
    }
  }


  /** Type class for raising dimensions to the power `1 / P` */
  trait Root[P <: XInt, H <: HList] {
    type Out <: HList
  }
  object Root {
    type Aux[P <: XInt, H <: HList, Out0 <: HList] = Root[P, H] { type Out = Out0 }

    implicit def dimensionsRootBase[P <: XInt]: Aux[P, HNil, HNil] = new Root[P, HNil] {
      type Out = HNil
    }

    implicit def dimensionsRootRecurse[P <: XInt, D, U <: Unit[_], F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[1, P], PF],
      ev1: Aux[P, T, TOut],
      ev2: fraction.Valid[PF]
    ): Aux[P, Term[D, U, F] :: T, Term[D, U, PF] :: TOut] = new Root[P, Term[D, U, F] :: T] {
      type Out = Term[D, U, PF] :: TOut
    }

    implicit def dimensionsRootRecurseInvalid[P <: XInt, D, U <: Unit[_], F <: Fraction[_, _], PF <: Fraction[_, _], T <: HList, TOut <: HList](
      implicit ev0: fraction.Multiply.Aux[F, Fraction[1, P], PF],
      ev1: Aux[P, T, TOut],
      ev2: Refute[fraction.Valid[PF]]
    ): Aux[P, Term[D, U, F] :: T, TOut] = new Root[P, Term[D, U, F] :: T] {
      type Out = TOut
    }
  }

  trait ConvertTo[A, UT <: Unit[_], H <: HList] {
    type Out <: HList
    def apply(a: A): A
  }

  object ConvertTo {
    type Aux[A, UT <: Unit[_], H <: HList, Out0 <: HList] = ConvertTo[A, UT, H] { type Out = Out0 }

    implicit def dimensionConvertTo[A, D, UF <: Unit[D], NF <: Singleton with Int, DF <: Singleton with Int, UT <: Unit[D], T <: HList]
    (implicit conversion: base.Conversion[A, D, UF, UT],
      ev3: Field[A],
      ev4: NRoot[A],
      n: ValueOf[NF],
      d: ValueOf[DF]
    ): Aux[A, UT, Term[D, UF, Fraction[NF, DF]] :: T, Term[D, UT, Fraction[NF, DF]] :: T] =
      new ConvertTo[A, UT, Term[D, UF, Fraction[NF, DF]] :: T] {
        type Out = Term[D, UT, Fraction[NF, DF]] :: T
        def apply(a: A): A = conversion.factor.pow(n.value).nroot(d.value) * a
      }

    implicit def dimensionConvertToRecurse[A, U <: Unit[_], H, T <: HList, TOut <: HList](
      implicit ev: Aux[A, U, T, TOut]
    ): Aux[A, U, H :: T, H :: TOut] =
      new ConvertTo[A, U, H :: T] {
        type Out = H :: TOut
        def apply(a: A): A = ev(a)
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

    implicit def dimensionsShowDimensionRecurse[D, U <: Unit[_], FN <: XInt, FD <: XInt, T <: HList](
      implicit showDimension: base.Show[D], showTail: ShowDimension[T],
      ev0: Require[FD != 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowDimension[Term[D, U, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showDimension()}^${numerator.value.toString}/${denominator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowDimensionRecurse1[D, U <: Unit[_], FN <: XInt, FD <: XInt, T <: HList](
      implicit showDimension: base.Show[D], showTail: ShowDimension[T],
      ev0: Require[FD == 1], ev1: LowPriority,
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowDimension[Term[D, U, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showDimension()}^${numerator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowDimensionRecurse2[D, U <: Unit[_], FN <: XInt, FD <: XInt, T <: HList](
      implicit showDimension: base.Show[D], showTail: ShowDimension[T],
      ev0: Require[FD == 1], ev1: Require[FN == 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowDimension[Term[D, U, Fraction[FN, FD]] :: T] {
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

    implicit def dimensionsShowUnitRecurse[D, U <: Unit[_], FN <: XInt, FD <: XInt, T <: HList](
      implicit showUnit: base.Show[U], showTail: ShowUnit[T],
      ev0: Require[FD != 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowUnit[Term[D, U, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showUnit()}^${numerator.value.toString}/${denominator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowUnitRecurse1[D, U <: Unit[_], FN <: XInt, FD <: XInt, T <: HList](
      implicit showUnit: base.Show[U], showTail: ShowUnit[T],
      ev0: Require[FD == 1], ev1: LowPriority,
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowUnit[Term[D, U, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showUnit()}^${numerator.value.toString} ${showTail()}"
      }

    implicit def dimensionsShowUnitRecurse2[D, U <: Unit[_], FN <: XInt, FD <: XInt, T <: HList](
      implicit showUnit: base.Show[U], showTail: ShowUnit[T],
      ev0: Require[FD == 1], ev1: Require[FN == 1],
      numerator: ValueOf[FN],
      denominator: ValueOf[FD]) =
      new ShowUnit[Term[D, U, Fraction[FN, FD]] :: T] {
        def apply(): String = s"${showUnit()} ${showTail()}"
      }
  }
}

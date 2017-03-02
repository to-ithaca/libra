package libra
package ops

import shapeless._
import singleton.ops._
import singleton.ops.impl._

object quantity {

  /**
   * Type class for setting the metric prefix of a Quantity `Q`.
   */
  trait Prefix[Q <: Quantity[_, _], I <: Singleton with Int] extends DepFn1[Q] {
    type Out <: Quantity[_, _]
  }
  object Prefix {
    type Aux[Q <: Quantity[_, _], I <: Singleton with Int, Out0 <: Quantity[_, _]] = Prefix[Q, I] { type Out = Out0 }

    implicit def quantityPrefix[Prev <: Singleton with Int, Next <: Singleton with Int, Diff <: Singleton with Int, D <: Dimensions[_, _]](
      implicit diff: Prev - Next
    ): Prefix.Aux[Quantity[Prev, D], Next, Quantity[Next, D]] =
      new Prefix[Quantity[Prev, D], Next] {
        type Out = Quantity[Next, D]
        //need to cast, as ValueOf cannot be resolved. Waiting for a fix in singleton ops macros
        def apply(q: Quantity[Prev, D]): Out = Quantity[Next, D](q.value * math.pow(10.0, diff.value.asInstanceOf[Int]))
    }
  }

  /**
   * Type class for adding quantities `L` and `R`.
   * This only exists if `L` and `R` have the same dimensions.
   *  If `L` and `R` have different mertric prefixes,the output has the highest prefix.
   */
  trait Add[L <: Quantity[_, _], R <: Quantity[_, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _]
  }

  object Add {
    type Aux[L <: Quantity[_, _], R <: Quantity[_, _], Out0 <: Quantity[_, _]] = Add[L, R] { type Out = Out0 }

    implicit def quantityAdd[IL <: Singleton with Int, DL <: Dimensions[_, _], IR <: Singleton with Int, DR <: Dimensions[_, _],
      IOut <: Singleton with Int](
      implicit ev: dimensions.Eq[DL, DR],
        max: OpInt.Aux[Max[IL, IR], IOut],
        lPrefix: Prefix.Aux[Quantity[IL, DL], IOut, Quantity[IOut, DL]],
        rPrefix: Prefix.Aux[Quantity[IR, DR], IOut, Quantity[IOut, DR]]
    ): Add.Aux[Quantity[IL, DL], Quantity[IR, DR], Quantity[IOut, DL]] =
      new Add[Quantity[IL, DL], Quantity[IR, DR]] {
        type Out = Quantity[IOut, DL]
        def apply(l: Quantity[IL, DL], r: Quantity[IR, DR]): Quantity[IOut, DL] =
          Quantity[IOut, DL](lPrefix(l).value + rPrefix(r).value)
      }
  }

  /**
    * Type class for multiplying quantities `L` and `R`
   */
  trait Multiply[L <: Quantity[_, _], R <: Quantity[_, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _]
  }

  object Multiply {
    type Aux[L <: Quantity[_, _], R <: Quantity[_, _], Out0 <: Quantity[_, _]] = Multiply[L, R] { type Out = Out0 }

    implicit def quantityMultiply[IL <: Singleton with Int, DL <: Dimensions[_, _], IR <: Singleton with Int, DR <: Dimensions[_, _], IOut <: Singleton with Int, DOut <: Dimensions[_, _]](
      implicit dimensionsM: dimensions.Multiply.Aux[DL, DR, DOut],
      prefixM: OpInt.Aux[IL + IR, IOut]
    ): Multiply.Aux[Quantity[IL, DL], Quantity[IR, DR], Quantity[IOut, DOut]] =
      new Multiply[Quantity[IL, DL], Quantity[IR, DR]] {
      type Out = Quantity[IOut, DOut]
      def apply(l: Quantity[IL, DL], r: Quantity[IR, DR]): Quantity[IOut, DOut] = Quantity[IOut, DOut](l.value * r.value)
    }
  }

  /** 
   * Type class for inverting a quantity `Q`
   */
  trait Invert[Q <: Quantity[_, _]] extends DepFn1[Q] {
    type Out <: Quantity[_, _]
  }
  object Invert {
    type Aux[Q <: Quantity[_, _], Out0 <: Quantity[_, _]] = Invert[Q] { type Out = Out0 }

    implicit def quantityInvert[P <: Singleton with Int, D <: Dimensions[_, _], PInv <: Singleton with Int, DInv <: Dimensions[_, _]](
      implicit dimensionsInv: dimensions.Invert.Aux[D, DInv],
      prefixInv: OpInt.Aux[Negate[P], PInv]
    ): Invert.Aux[Quantity[P, D], Quantity[PInv, DInv]] = 
      new Invert[Quantity[P, D]] {
        type Out = Quantity[PInv, DInv]
        def apply(q: Quantity[P, D]): Quantity[PInv, DInv] = Quantity[PInv, DInv](1.0 / q.value)
      }
  }

  /** 
   * Type class for dividing quantity `L` by `R`
   */
  trait Divide[L <: Quantity[_, _], R <: Quantity[_, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _]
  }
  object Divide {
    type Aux[L <: Quantity[_, _], R <: Quantity[_, _], Out0 <: Quantity[_, _]] = Divide[L, R] { type Out = Out0 }

    implicit def quantityDivide[L <: Quantity[_, _], R <: Quantity[_, _], RInv <: Quantity[_, _], QOut <: Quantity[_, _]](
      implicit invert: Invert.Aux[R, RInv],
      multiply: Multiply.Aux[L, RInv, QOut]
    ): Divide.Aux[L, R, QOut] =
      new Divide[L, R] {
        type Out = QOut
        def apply(l: L, r: R): Out = multiply(l, invert(r))
      }
  }

  /**
   * Type class for raising a quantity `Q` to the power `P`
   */
  trait Power[Q <: Quantity[_, _], P <: Singleton with Int] extends DepFn1[Q] {
    type Out <: Quantity[_, _]
  }
  object Power {
    type Aux[Q <: Quantity[_, _], P <: Singleton with Int, Out0 <: Quantity[_, _]] = Power[Q, P] { type Out = Out0 }

    implicit def quantityPower[Prefix <: Singleton with Int, D <: Dimensions[_, _], Pow <: Singleton with Int, DOut <: Dimensions[_, _], PrefixOut <: Singleton with Int](
      implicit dimensionsP: dimensions.Power.Aux[D, Pow, DOut],
      prefixP: OpInt.Aux[Prefix * Pow, PrefixOut],
      powValue: ValueOf[Pow]
    ): Power.Aux[Quantity[Prefix, D], Pow, Quantity[PrefixOut, DOut]] =
      new Power[Quantity[Prefix, D], Pow] {
        type Out = Quantity[PrefixOut, DOut]
        def apply(q: Quantity[Prefix, D]): Out = Quantity[PrefixOut, DOut](math.pow(q.value, powValue.value))
      }
  }

  /**
   * Type class for showing a quantity `Q`
   */
  trait Show[Q <: Quantity[_, _]] {
    def apply(q: Q): String
  }

  object Show {
    implicit def quantityShow[P <: Singleton with Int, D <: Dimensions[_, _]](
      implicit value: ValueOf[P],
      showUnit: dimensions.ShowUnit[D],
      showDimension: dimensions.ShowDimension[D]
    ): Show[Quantity[P, D]] =
      new Show[Quantity[P, D]] {
        def apply(q: Quantity[P, D]): String = 
          s"${q.value} * 10^${value.value} ${showUnit()} [${showDimension()}]"
      }
  }
}

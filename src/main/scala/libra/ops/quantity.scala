package libra
package ops

import shapeless._
import singleton.ops._
import singleton.ops.impl._

import spire.algebra._
import spire.math._
import spire.implicits._

object quantity {

  /**
   * Type class for setting the metric prefix of a Quantity `Q`.
   */
  trait Prefix[Q <: Quantity[_, _, _], I <: Singleton with Int] extends DepFn1[Q] {
    type Out <: Quantity[_, _, _]
  }
  object Prefix {
    type Aux[Q <: Quantity[_, _, _], I <: Singleton with Int, Out0 <: Quantity[_, _, _]] = Prefix[Q, I] { type Out = Out0 }

    implicit def quantityPrefix[A, Prev <: Singleton with Int, Next <: Singleton with Int, Diff <: Singleton with Int, D <: Dimensions[_, _]](
      implicit ev0: MultiplicativeSemigroup[A], convert: ConvertableTo[A], diff: Prev - Next,
    ): Prefix.Aux[Quantity[A, Prev, D], Next, Quantity[A, Next, D]] =
      new Prefix[Quantity[A, Prev, D], Next] {
        type Out = Quantity[A, Next, D]
        //need to cast, as ValueOf cannot be resolved. Waiting for a fix in singleton ops macros
        def apply(q: Quantity[A, Prev, D]): Out = Quantity[A, Next, D](q.value * convert.fromDouble(math.pow(10.0, diff.value.asInstanceOf[Int])))
    }
  }

  /**
   * Type class for adding quantities `L` and `R`.
   * This only exists if `L` and `R` have the same dimensions.
   *  If `L` and `R` have different mertric prefixes,the output has the highest prefix.
   */
  trait Add[L <: Quantity[_, _, _], R <: Quantity[_, _, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _, _]
  }

  object Add {
    type Aux[L <: Quantity[_, _, _], R <: Quantity[_, _, _], Out0 <: Quantity[_, _, _]] = Add[L, R] { type Out = Out0 }

    implicit def quantityAdd[A, IL <: Singleton with Int, DL <: Dimensions[_, _], IR <: Singleton with Int, DR <: Dimensions[_, _],
      IOut <: Singleton with Int](
      implicit ev0: AdditiveSemigroup[A],
        ev1: dimensions.Eq[DL, DR],
        max: OpInt.Aux[Max[IL, IR], IOut],
        lPrefix: Prefix.Aux[Quantity[A, IL, DL], IOut, Quantity[A, IOut, DL]],
        rPrefix: Prefix.Aux[Quantity[A, IR, DR], IOut, Quantity[A, IOut, DR]]
    ): Add.Aux[Quantity[A, IL, DL], Quantity[A, IR, DR], Quantity[A, IOut, DL]] =
      new Add[Quantity[A, IL, DL], Quantity[A, IR, DR]] {
        type Out = Quantity[A, IOut, DL]
        def apply(l: Quantity[A, IL, DL], r: Quantity[A, IR, DR]): Quantity[A, IOut, DL] =
          Quantity[A, IOut, DL](lPrefix(l).value + rPrefix(r).value)
      }
  }

  /**
    * Type class for multiplying quantities `L` and `R`
   */
  trait Multiply[L <: Quantity[_, _, _], R <: Quantity[_, _, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _, _]
  }

  object Multiply {
    type Aux[L <: Quantity[_, _, _], R <: Quantity[_, _, _], Out0 <: Quantity[_, _, _]] = Multiply[L, R] { type Out = Out0 }

    implicit def quantityMultiply[A, IL <: Singleton with Int, DL <: Dimensions[_, _], IR <: Singleton with Int, DR <: Dimensions[_, _], IOut <: Singleton with Int, DOut <: Dimensions[_, _]](
      implicit ev: MultiplicativeSemigroup[A],
      dimensionsM: dimensions.Multiply.Aux[DL, DR, DOut],
      prefixM: OpInt.Aux[IL + IR, IOut]
    ): Multiply.Aux[Quantity[A, IL, DL], Quantity[A, IR, DR], Quantity[A, IOut, DOut]] =
      new Multiply[Quantity[A, IL, DL], Quantity[A, IR, DR]] {
      type Out = Quantity[A, IOut, DOut]
      def apply(l: Quantity[A, IL, DL], r: Quantity[A, IR, DR]): Quantity[A, IOut, DOut] = Quantity[A, IOut, DOut](l.value * r.value)
    }
  }

  /** 
   * Type class for inverting a quantity `Q`
   */
  trait Invert[Q <: Quantity[_, _, _]] extends DepFn1[Q] {
    type Out <: Quantity[_, _, _]
  }
  object Invert {
    type Aux[Q <: Quantity[_, _, _], Out0 <: Quantity[_, _, _]] = Invert[Q] { type Out = Out0 }

    implicit def quantityInvert[A, P <: Singleton with Int, D <: Dimensions[_, _], PInv <: Singleton with Int, DInv <: Dimensions[_, _]](
      implicit ev: Field[A], convert: ConvertableTo[A],
        dimensionsInv: dimensions.Invert.Aux[D, DInv],
      prefixInv: OpInt.Aux[Negate[P], PInv]
    ): Invert.Aux[Quantity[A, P, D], Quantity[A, PInv, DInv]] = 
      new Invert[Quantity[A, P, D]] {
        type Out = Quantity[A, PInv, DInv]
        def apply(q: Quantity[A, P, D]): Quantity[A, PInv, DInv] = Quantity[A, PInv, DInv](convert.fromInt(1) / q.value)
      }
  }

  /** 
   * Type class for dividing quantity `L` by `R`
   */
  trait Divide[L <: Quantity[_, _, _], R <: Quantity[_, _, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _, _]
  }
  object Divide {
    type Aux[L <: Quantity[_, _, _], R <: Quantity[_, _, _], Out0 <: Quantity[_, _, _]] = Divide[L, R] { type Out = Out0 }

    implicit def quantityDivide[L <: Quantity[_, _, _], R <: Quantity[_, _, _], RInv <: Quantity[_, _, _], QOut <: Quantity[_, _, _]](
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
  trait Power[Q <: Quantity[_, _, _], P <: Singleton with Int] extends DepFn1[Q] {
    type Out <: Quantity[_, _, _]
  }
  object Power {
    type Aux[Q <: Quantity[_, _, _], P <: Singleton with Int, Out0 <: Quantity[_, _, _]] = Power[Q, P] { type Out = Out0 }

    implicit def quantityPower[A, Prefix <: Singleton with Int, D <: Dimensions[_, _], Pow <: Singleton with Int, DOut <: Dimensions[_, _], PrefixOut <: Singleton with Int](
      implicit ev: ConvertableFrom[A], convert: ConvertableTo[A],
      dimensionsP: dimensions.Power.Aux[D, Pow, DOut],
      prefixP: OpInt.Aux[Prefix * Pow, PrefixOut],
      powValue: ValueOf[Pow]
    ): Power.Aux[Quantity[A, Prefix, D], Pow, Quantity[A, PrefixOut, DOut]] =
      new Power[Quantity[A, Prefix, D], Pow] {
        type Out = Quantity[A, PrefixOut, DOut]
        def apply(q: Quantity[A, Prefix, D]): Out = Quantity[A, PrefixOut, DOut](convert.fromDouble(math.pow(q.value.toDouble, powValue.value)))
      }
  }

  /**
   * Type class for showing a quantity `Q`
   */
  trait Show[Q <: Quantity[_, _, _]] {
    def apply(q: Q): String
  }

  object Show {
    implicit def quantityShow[A, P <: Singleton with Int, D <: Dimensions[_, _]](
      implicit value: ValueOf[P],
      showUnit: dimensions.ShowUnit[D],
      showDimension: dimensions.ShowDimension[D]
    ): Show[Quantity[A, P, D]] =
      new Show[Quantity[A, P, D]] {
        def apply(q: Quantity[A, P, D]): String = 
          s"${q.value} * 10^${value.value} ${showUnit()} [${showDimension()}]"
      }
  }
}

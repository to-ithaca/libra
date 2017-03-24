package libra
package ops

import shapeless._
import shapeless.ops.hlist.Align
import singleton.ops._
import singleton.ops.impl._

import spire.algebra._
import spire.math._
import spire.implicits._

object quantity {

  /**
   * Type class for adding quantities `L` and `R`.
   * This only exists if `L` and `R` have the same dimensions.
   *  If `L` and `R` have different mertric prefixes,the output has the highest prefix.
   */
  @annotation.implicitNotFound(msg = """These quantities can't be added!  Most likely they have different dimensions.  If not, make sure that there's an implicit AdditiveSemigroup in scope.
Left: ${L}
Right: ${R}""")
  trait Add[L <: Quantity[_, _], R <: Quantity[_, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _]
  }

  object Add {
    type Aux[L <: Quantity[_, _], R <: Quantity[_, _], Out0 <: Quantity[_, _]] = Add[L, R] { type Out = Out0 }

    implicit def quantityAdd[A, DL <: HList, DR <: HList](
      implicit ev0: AdditiveSemigroup[A],
        ev1: Align[DL, DR]
    ): Add.Aux[Quantity[A, DL], Quantity[A, DR], Quantity[A, DL]] =
      new Add[Quantity[A, DL], Quantity[A, DR]] {
        type Out = Quantity[A, DL]
        def apply(l: Quantity[A, DL], r: Quantity[A, DR]): Quantity[A, DL] =
          Quantity[A, DL](l.value + r.value)
      }
  }

  /**
    * Type class for multiplying quantities `L` and `R`
   */

  @annotation.implicitNotFound(msg = "No implicit MultiplicativeSemigroup in scope.  Make sure you import spire.implicits._")
  trait Multiply[L <: Quantity[_, _], R <: Quantity[_, _]] extends DepFn2[L, R] {
    type Out <: Quantity[_, _]
  }

  object Multiply {
    type Aux[L <: Quantity[_, _], R <: Quantity[_, _], Out0 <: Quantity[_, _]] = Multiply[L, R] { type Out = Out0 }

    implicit def quantityMultiply[A, DL <: HList, DR <: HList, DOut <: HList](
      implicit ev: MultiplicativeSemigroup[A],
      dimensionsM: dimensions.Multiply.Aux[DL, DR, DOut]
    ): Multiply.Aux[Quantity[A, DL], Quantity[A, DR], Quantity[A, DOut]] =
      new Multiply[Quantity[A, DL], Quantity[A, DR]] {
      type Out = Quantity[A, DOut]
      def apply(l: Quantity[A, DL], r: Quantity[A, DR]): Quantity[A, DOut] = Quantity[A, DOut](l.value * r.value)
    }
  }

  /** 
   * Type class for inverting a quantity `Q`
   */
  @annotation.implicitNotFound(msg = "No implicit Field in scope.  Make sure you import spire.implicits._")
  trait Invert[Q <: Quantity[_, _]] extends DepFn1[Q] {
    type Out <: Quantity[_, _]
  }
  object Invert {
    type Aux[Q <: Quantity[_, _], Out0 <: Quantity[_, _]] = Invert[Q] { type Out = Out0 }

    implicit def quantityInvert[A, D <: HList, DInv <: HList](
      implicit ev: Field[A], convert: ConvertableTo[A],
        dimensionsInv: dimensions.Invert.Aux[D, DInv]
    ): Invert.Aux[Quantity[A, D], Quantity[A, DInv]] = 
      new Invert[Quantity[A, D]] {
        type Out = Quantity[A, DInv]
        def apply(q: Quantity[A, D]): Quantity[A, DInv] = Quantity[A, DInv](convert.fromInt(1) / q.value)
      }
  }

  /** 
   * Type class for dividing quantity `L` by `R`
   */

  trait Divide[L <: Quantity[_, _], R <: Quantity[_, _]] {
    type Out <: Quantity[_, _]
    def apply(l: L, r: R): Out
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
  trait Power[Q <: Quantity[_, _], P <: XInt] extends DepFn1[Q] {
    type Out <: Quantity[_, _]
  }
  object Power {
    type Aux[Q <: Quantity[_, _], P <: XInt, Out0 <: Quantity[_, _]] = Power[Q, P] { type Out = Out0 }

    implicit def quantityPower[A, D <: HList, Pow <: XInt, DOut <: HList](
      implicit ev: ConvertableFrom[A], convert: ConvertableTo[A],
      dimensionsP: dimensions.Power.Aux[Pow, D, DOut],
      powValue: ValueOf[Pow]
    ): Power.Aux[Quantity[A, D], Pow, Quantity[A, DOut]] =
      new Power[Quantity[A, D], Pow] {
        type Out = Quantity[A, DOut]
        def apply(q: Quantity[A, D]): Out = Quantity[A, DOut](convert.fromDouble(math.pow(q.value.toDouble, powValue.value)))
      }
  }

  trait Conversion[A, D, 

  /**
   * Type class for showing a quantity `Q`
   */
  @annotation.implicitNotFound(msg = "Some base units can't be shown. Make sure you import libra.si._ when working with units.")
  trait Show[Q <: Quantity[_, _]] {
    def apply(q: Q): String
  }

  object Show {
    implicit def quantityShow[A, D <: HList](
      implicit showUnit: dimensions.ShowUnit[D],
      showDimension: dimensions.ShowDimension[D]
    ): Show[Quantity[A, D]] =
      new Show[Quantity[A, D]] {
        def apply(q: Quantity[A, D]): String = 
          s"${q.value} ${showUnit().trim} [${showDimension().trim}]"
      }
  }
}

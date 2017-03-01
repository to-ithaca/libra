package libra
package ops

import shapeless._
import shapeless.ops.hlist._
import singleton.ops._
import singleton.ops.impl._

object dimensions {

  /**
   * Type class witnessing that two dimensions `L` and `R` have the same elements 
   */
  trait Eq[L <: Dimensions[_, _], R <: Dimensions[_, _]]

  object Eq {
    implicit def dimensionsEq[NL <: HList, DL <: HList, NR <: HList, DR <: HList](
      implicit evN: Align[NL, NR], evD: Align[DL, DR])
        : Eq[Dimensions[NL, DL], Dimensions[NR, DR]] =
      new Eq[Dimensions[NL, DL], Dimensions[NR, DR]] {}
  }

  /**
   * Type class to simplify the numerator and denominator of dimensions `D`.
   * 
   * The output contains no shared elements in the numerator and denominator.
   */
  trait Simplify[D <: Dimensions[_,_]] {
    type Out <: Dimensions[_,_]
  }

  object Simplify {

    type Aux[D <: Dimensions[_, _], Out0 <: Dimensions[_, _]] = Simplify[D] { type Out = Out0 }

    implicit def dimensionsSimplify[N <: HList, D <: HList, NS <: HList, DS <: HList](
      implicit ev0: Diff.Aux[N, D, NS], ev1: Diff.Aux[D, N, DS]
    ): Simplify.Aux[Dimensions[N, D], Dimensions[NS, DS]] = new Simplify[Dimensions[N, D]] {
      type Out = Dimensions[NS, DS]
    }
  }


  /**
   * Type class to multiply dimensions `L` and `R` together.
   * 
   * The output contains a simplified combination of elements in `L` appended to elements in `R`
   */
  trait Multiply[L <: Dimensions[_, _], R <: Dimensions[_, _]] {
    type Out <: Dimensions[_, _]
  }

  object Multiply {
    type Aux[L <: Dimensions[_, _], R <: Dimensions[_, _], Out0 <: Dimensions[_, _]] = Multiply[L, R] { type Out = Out0 }

    implicit def dimensionsMultiply[NL <: HList, DL <: HList, NR <: HList, DR <: HList, NOut <: HList, DOut <: HList,
      DimOut <: Dimensions[_, _]](
      implicit evN: Prepend.Aux[NL, NR, NOut], evD: Prepend.Aux[DL, DR, DOut], s: Simplify.Aux[Dimensions[NOut, DOut], DimOut]
    ) : Multiply.Aux[Dimensions[NL, DL], Dimensions[NR, DR], DimOut] =
      new Multiply[Dimensions[NL, DL], Dimensions[NR, DR]] {
        type Out = DimOut
      }
  }

  /**
   * Type class to invert dimensions `D`.
   * 
   * The inverse of `Dimensions[N, D]` is `Dimensions[D, N]`
   */
  trait Invert[D <: Dimensions[_, _]] {
    type Out <: Dimensions[_, _]
  }

  object Invert {
    type Aux[D <: Dimensions[_, _], Out0 <: Dimensions[_, _]] = Invert[D] { type Out = Out0 }

    implicit def dimensionsInvert[N <: HList, D <: HList]: Invert.Aux[Dimensions[N, D], Dimensions[D, N]] =
      new Invert[Dimensions[N, D]] {
        type Out = Dimensions[D, N]
      }
  }

  /**
   * Type class to divide dimensions `L` by `R`.
   */
  trait Divide[L <: Dimensions[_, _], R <: Dimensions[_, _]] {
    type Out <: Dimensions[_, _]
  }

  object Divide {
    type Aux[L <: Dimensions[_, _], R <: Dimensions[_, _], Out0 <: Dimensions[_, _]] = Divide[L, R] { type Out = Out0 }

    implicit def dimensionsDivide[NL <: HList, NR <: HList, DL <: HList, DR <: HList, DimRInv <: Dimensions[_, _], DimOut <: Dimensions[_, _]](
      implicit inv: Invert.Aux[Dimensions[NR, DR], DimRInv],
      m: Multiply.Aux[Dimensions[NL, DL], DimRInv, DimOut]
    ): Divide.Aux[Dimensions[NL, DL], Dimensions[NR, DR], DimOut] = 
      new Divide[Dimensions[NL, DL], Dimensions[NR, DR]] {
      type Out = DimOut
    }
  }

  /**
    * Type class to raise dimensions `D` to the power `P`.
    */
  trait Power[D <: Dimensions[_, _], P <: Singleton with Int] {
    type Out <: Dimensions[_, _]
  }
  
  object Power {
    type Aux[D <: Dimensions[_, _], P <: Singleton with Int, Out0 <: Dimensions[_, _]] = Power[D, P] { type Out = Out0 }

    implicit def dimensionsPower0[D <: Dimensions[_, _]]: Power.Aux[D, 0, Dimensions[HNil, HNil]] =
      new Power[D, 0] {
        type Out = Dimensions[HNil, HNil]
      }

    implicit def dimensionsPowerPositiveN[D <: Dimensions[_, _], P <: Singleton with Int, PPred <: Singleton with Int, PowPred <: Dimensions[_, _], DOut <: Dimensions[_, _]](
      implicit evPos: Require[P > 0],
      pred: OpInt.Aux[P - 1, PPred],
      powPred: Power.Aux[D, PPred, PowPred],
      m: Multiply.Aux[D, PowPred, DOut]
    ): Power.Aux[D, P, DOut] = new Power[D, P] {
      type Out = DOut
    }

    implicit def dimensionsPowerNegativeN[D <: Dimensions[_, _], PNeg <: Singleton with Int, PPos <: Singleton with Int,
      PowPos <: Dimensions[_, _], DOut <: Dimensions[_, _]](
      implicit evNeg: Require[PNeg < 0],
        negate: OpInt.Aux[Negate[PNeg], PPos],
        powPos: Power.Aux[D, PPos, PowPos],
        invert: Invert.Aux[PowPos, DOut]
    ): Power.Aux[D, PNeg, DOut] = new Power[D, PNeg] {
      type Out = DOut
    }
  }

  type Numerator
  type Denominator

  /**
    * Type class representing a single term within a dimensions string representation.
    *  `D` is the dimension for the term, `I` is the number of apperances, 
    *  `S` represents whether the term appears in the numerator or the denominator.
    */
  trait Term[D, I <: Nat, S[_] <: base.Show[_], A] {
    def apply(): String
  }
  
  object Term {

    implicit def numeratorTerm[D, I <: Nat, S[_] <: base.Show[_]](
      implicit dShow: S[D],
      iValue: shapeless.ops.nat.ToInt[I]): Term[D, I, S, Numerator] =
      new Term[D, I, S, Numerator] {
        def apply(): String = s"${dShow()}^${iValue()}"
      }

    implicit def denominatorTerm[D, I <: Nat, S[_] <: base.Show[_]](
      implicit dShow: S[D],
      iValue: shapeless.ops.nat.ToInt[I]): Term[D, I, S, Denominator] =
      new Term[D, I, S, Denominator] {
        def apply(): String = s"${dShow()}^-${iValue()}"
      }
  }

  /**
    * Type class represeting a list of terms within a dimensions string.
    *  `L` is the list of dimensions
    *  `A` indicates whether the list is the Numerator or the Denominator
    */
  trait TermList[L <: HList, S[_] <: base.Show[_], A] {
    def apply(): String
  }

  object TermList {
    implicit def hnilTermList[S[_] <: base.Show[_], A]: TermList[HNil, S, A] = new TermList[HNil, S, A] {
      def apply(): String = ""
    }

    implicit def hlistTermList[H, T <: HList, S[_] <: base.Show[_], A, Prefix <: HList, Suffix <: HList, Pow <: Nat](
      implicit ev0: Partition.Aux[H :: T, H, Prefix, Suffix],
      ev1: Length.Aux[Prefix, Pow],
      term: Term[H, Pow, S, A],
      tail: TermList[Suffix, S, A]
    ): TermList[H :: T, S, A] =
      new TermList[H :: T, S, A] {
        def apply(): String = s"${term()} ${tail()}"
      }
  }

  /**
    * Type class for showing the dimension of dimensions `D`.
    * e.g. velocity has dimensions L^1 T^-1
    */
  trait ShowDimension[D] {
    def apply(): String
  }

  object ShowDimension {

    implicit def dimensionsShowDimension[N <: HList, D <: HList](
      implicit numerator: TermList[N, base.ShowDimension, Numerator],
      denominator: TermList[D, base.ShowDimension, Denominator]
    ): ShowDimension[Dimensions[N, D]] = new ShowDimension[Dimensions[N, D]] {
      def apply(): String = s"${numerator()} ${denominator()}".trim
    }
  }

  trait ShowUnit[D] {
    def apply(): String
  }

  object ShowUnit {
    implicit def dimensionsShowUnit[N <: HList, D <: HList](
      implicit numerator: TermList[N, base.ShowUnit, Numerator],
      denominator: TermList[D, base.ShowUnit, Denominator]
    ): ShowUnit[Dimensions[N, D]] = new ShowUnit[Dimensions[N, D]] {
      def apply(): String = s"${numerator()} ${denominator()}".trim
    }
  }
}

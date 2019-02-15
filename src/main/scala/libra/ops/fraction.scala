package libra
package ops

import singleton.ops._
import singleton.ops.impl._

object fraction {

  /** Typeclass for finding the greatest common divisor of two numbers using Euclid's algorithm.
    *
    * @tparam A a non-zero natural number
    * @tparam B another non-zero natural number
    */
  trait GCD[A <: XInt, B <: XInt] {
    type Out <: XInt
  }

  object GCD {
    type Aux[A <: XInt, B <: XInt, Out0 <: XInt] = GCD[A, B] { type Out = Out0 }

    implicit def fractionBaseGCD[A <: XInt, B <: XInt, Rem <: XInt](
        implicit ev0: Require[A >= B],
        ev1: OpInt.Aux[A % B, Rem],
        ev2: Require[Rem == W.`0`.T]): Aux[A, B, B] = new GCD[A, B] {
      type Out = B
    }

    implicit def fractionRecurseGCD[A <: XInt,
                                    B <: XInt,
                                    Rem <: XInt,
                                    D <: XInt](
        implicit ev0: Require[A >= B],
        ev1: OpInt.Aux[A % B, Rem],
        ev2: Require[Rem != W.`0`.T],
        ev3: Aux[B, Rem, D]): Aux[A, B, D] = new GCD[A, B] {
      type Out = D
    }

    implicit def fractionRecurseGCD1[A <: XInt,
                                     B <: XInt,
                                     Rem <: XInt,
                                     D <: XInt](
        implicit ev0: Require[B < A],
        ev1: Aux[A, B, D]): Aux[B, A, D] = new GCD[B, A] {
      type Out = D
    }
  }

  /** Typeclass for negating a fraction */
  trait Negate[F <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Negate {
    type Aux[F <: Fraction[_, _], Out0 <: Fraction[_, _]] = Negate[F] {
      type Out = Out0
    }

    implicit def fractionNegate[N <: XInt, D <: XInt, NN <: XInt](
        implicit ev: OpInt.Aux[W.`0`.T - N, NN]
    ): Aux[Fraction[N, D], Fraction[NN, D]] = new Negate[Fraction[N, D]] {
      type Out = Fraction[NN, D]
    }
  }

  /** Typeclass to simplify a fraction */
  trait Simplify[F <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Simplify {
    type Aux[F <: Fraction[_, _], Out0 <: Fraction[_, _]] = Simplify[F] {
      type Out = Out0
    }

    implicit def fractionSimplifyPositive[N <: XInt,
                                          D <: XInt,
                                          C <: XInt,
                                          SN <: XInt,
                                          SD <: XInt](
        implicit ev0: Require[N > W.`0`.T],
        gcd: GCD.Aux[N, D, C],
        n: OpInt.Aux[N / C, SN],
        d: OpInt.Aux[D / C, SD]
    ): Aux[Fraction[N, D], Fraction[SN, SD]] = new Simplify[Fraction[N, D]] {
      type Out = Fraction[SN, SD]
    }

    implicit def fractionSimplifyNegative[N <: XInt,
                                          D <: XInt,
                                          F <: Fraction[_, _],
                                          SNF <: Fraction[_, _],
                                          SF <: Fraction[_, _]](
        implicit ev: Require[N < W.`0`.T],
        ev1: Negate.Aux[Fraction[N, D], F],
        ev2: Aux[F, SNF],
        ev3: Negate.Aux[SNF, SF]): Aux[Fraction[N, D], SF] =
      new Simplify[Fraction[N, D]] {
        type Out = SF
      }

    implicit def fractionSimplifyZero[D <: XInt]
      : Aux[Fraction[W.`0`.T, D], Fraction[W.`0`.T, D]] = new Simplify[Fraction[W.`0`.T, D]] {
      type Out = Fraction[W.`0`.T, D]
    }
  }

  /** Typeclass to add two fractions */
  trait Add[L <: Fraction[_, _], R <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Add {
    type Aux[L <: Fraction[_, _], R <: Fraction[_, _], Out0 <: Fraction[_, _]] =
      Add[L, R] { type Out = Out0 }

    implicit def fractionAdd[LN <: XInt,
                             LD <: XInt,
                             RN <: XInt,
                             RD <: XInt,
                             LNRD <: XInt,
                             RNLD <: XInt,
                             N <: XInt,
                             D <: XInt,
                             F <: Fraction[_, _]](
        implicit ev0: OpInt.Aux[LN * RD, LNRD],
        ev1: OpInt.Aux[RN * LD, RNLD],
        ev2: OpInt.Aux[LNRD + RNLD, N],
        ev3: OpInt.Aux[LD * RD, D],
        ev4: Simplify.Aux[Fraction[N, D], F]
    ): Aux[Fraction[LN, LD], Fraction[RN, RD], F] =
      new Add[Fraction[LN, LD], Fraction[RN, RD]] {
        type Out = F
      }
  }

  /** Typeclass for subtracting fractions */
  trait Subtract[L <: Fraction[_, _], R <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Subtract {
    type Aux[L <: Fraction[_, _], R <: Fraction[_, _], Out0 <: Fraction[_, _]] =
      Subtract[L, R] { type Out = Out0 }

    implicit def fractionSubtract[L <: Fraction[_, _],
                                  R <: Fraction[_, _],
                                  NR <: Fraction[_, _],
                                  F <: Fraction[_, _]](
        implicit ev0: Negate.Aux[R, NR],
        ev1: Add.Aux[L, NR, F]
    ): Aux[L, R, F] = new Subtract[L, R] {
      type Out = F
    }
  }

  /** Typeclass to multiply two fractions */
  trait Multiply[L <: Fraction[_, _], R <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Multiply {
    type Aux[L <: Fraction[_, _], R <: Fraction[_, _], Out0 <: Fraction[_, _]] =
      Multiply[L, R] { type Out = Out0 }

    implicit def fractionMultiply[LN <: XInt,
                                  LD <: XInt,
                                  RN <: XInt,
                                  RD <: XInt,
                                  N <: XInt,
                                  D <: XInt,
                                  F <: Fraction[_, _]](
        implicit ev0: OpInt.Aux[LN * RN, N],
        ev1: OpInt.Aux[LD * RD, D],
        ev2: Simplify.Aux[Fraction[N, D], F]
    ): Aux[Fraction[LN, LD], Fraction[RN, RD], F] =
      new Multiply[Fraction[LN, LD], Fraction[RN, RD]] {
        type Out = F
      }
  }

  /** Typeclass to determine if a fraction is non-zero and finite */
  trait Valid[F <: Fraction[_, _]]

  object Valid {
    implicit def valid[FN <: XInt, FD <: XInt](
        implicit ev0: Require[FN != W.`0`.T],
        ev1: Require[FD != W.`0`.T]): Valid[Fraction[FN, FD]] =
      new Valid[Fraction[FN, FD]] {}
  }
}

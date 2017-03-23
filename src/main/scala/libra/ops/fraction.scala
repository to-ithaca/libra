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
  trait GCD[A <: Singleton with Int, B <: Singleton with Int] {
    type Out <: Singleton with Int
  }

  object GCD {
    type Aux[A <: Singleton with Int, B <: Singleton with Int, Out0 <: Singleton with Int] = GCD[A, B] { type Out = Out0 }

    implicit def fractionBaseGCD[A <: Singleton with Int, B <: Singleton with Int, Rem <: Singleton with Int](
      implicit ev0: Require[A >= B],
      ev1: OpInt.Aux[A % B, Rem],
      ev2: Require[Rem == 0]): Aux[A, B, B] = new GCD[A, B] {
      type Out = B
    }

    implicit def fractionRecurseGCD[A <: Singleton with Int, B <: Singleton with Int, Rem <: Singleton with Int, D <: Singleton with Int](
      implicit ev0: Require[A >= B],
      ev1: OpInt.Aux[A % B, Rem],
      ev2: Require[Rem != 0],
      ev3: Aux[B, Rem, D]): Aux[A, B, D] = new GCD[A, B] {
      type Out = D
    }

    implicit def fractionRecurseGCD1[A <: Singleton with Int, B <: Singleton with Int, Rem <: Singleton with Int, D <: Singleton with Int](
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
    type Aux[F <: Fraction[_, _], Out0 <: Fraction[_, _]] = Negate[F] { type Out = Out0 }

    implicit def fractionNegate[N <: Singleton with Int, D <: Singleton with Int, NN <: Singleton with Int](
      implicit ev: OpInt.Aux[0 - N, NN]
    ): Aux[Fraction[N, D], Fraction[NN, D]] = new Negate[Fraction[N, D]] {
      type Out = Fraction[NN, D]
    }
  }

  /** Typeclass to simplify a fraction */
  trait Simplify[F <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Simplify {
    type Aux[F <: Fraction[_, _], Out0 <: Fraction[_, _]] = Simplify[F] { type Out = Out0 }

    implicit def fractionSimplifyPositive[N <: Singleton with Int, D <: Singleton with Int, C <: Singleton with Int, SN <: Singleton with Int,
 SD <: Singleton with Int](
      implicit ev0: Require[N > 0], 
      gcd: GCD.Aux[N, D, C],
      n: OpInt.Aux[N / C, SN],
      d: OpInt.Aux[D / C, SD]
    ): Aux[Fraction[N, D], Fraction[SN, SD]] = new Simplify[Fraction[N, D]] {
      type Out = Fraction[SN, SD]
    }

    implicit def fractionSimplifyNegative[N <: Singleton with Int, D <: Singleton with Int, F <: Fraction[_, _], SNF <: Fraction[_, _], SF <: Fraction[_, _]](
      implicit ev: Require[N < 0],
      ev1: Negate.Aux[Fraction[N, D], F],
      ev2: Aux[F, SNF],
      ev3: Negate.Aux[SNF, SF]): Aux[Fraction[N, D], SF] = new Simplify[Fraction[N, D]] {
      type Out = SF
    }

    implicit def fractionSimplifyZero[D <: Singleton with Int]: Aux[Fraction[0, D], Fraction[0, D]] = new Simplify[Fraction[0, D]] {
      type Out = Fraction[0, D]
    }
  }

  /** Typeclass to add two fractions */
  trait Add[L <: Fraction[_, _], R <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Add {
    type Aux[L <: Fraction[_, _], R <: Fraction[_, _], Out0 <: Fraction[_, _]] = Add[L, R] { type Out = Out0 }

    implicit def fractionAdd[LN <: Singleton with Int, LD <: Singleton with Int, RN <: Singleton with Int, RD <: Singleton with Int,
      LNRD <: Singleton with Int, RNLD <: Singleton with Int,
      N <: Singleton with Int, D <: Singleton with Int, F <: Fraction[_, _]](
      implicit ev0: OpInt.Aux[LN * RD, LNRD],
      ev1: OpInt.Aux[RN * LD, RNLD],
      ev2: OpInt.Aux[LNRD + RNLD, N],
      ev3: OpInt.Aux[LD * RD, D],
      ev4: Simplify.Aux[Fraction[N, D], F]
    ): Aux[Fraction[LN, LD], Fraction[RN, RD], F] = new Add[Fraction[LN, LD], Fraction[RN, RD]] {
      type Out = F
    }
  }

  /** Typeclass for subtracting fractions */
  trait Subtract[L <: Fraction[_, _], R <: Fraction[_, _]] {
    type Out <: Fraction[_, _]
  }

  object Subtract {
    type Aux[L <: Fraction[_, _], R <: Fraction[_, _], Out0 <: Fraction[_, _]] = Subtract[L, R] { type Out = Out0 }

    implicit def fractionSubtract[L <: Fraction[_, _], R <: Fraction[_, _], NR <: Fraction[_, _], F <: Fraction[_, _]](
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
    type Aux[L <: Fraction[_, _], R <: Fraction[_, _], Out0 <: Fraction[_, _]] = Multiply[L, R] { type Out = Out0 }

    implicit def fractionMultiply[LN <: Singleton with Int, LD <: Singleton with Int, RN <: Singleton with Int, RD <: Singleton with Int,
    N <: Singleton with Int, D <: Singleton with Int, F <: Fraction[_, _]](
      implicit ev0: OpInt.Aux[LN * RN, N],
      ev1: OpInt.Aux[LD * RD, D],
      ev2: Simplify.Aux[Fraction[N, D], F]
    ): Aux[Fraction[LN, LD], Fraction[RN, RD], F] = new Multiply[Fraction[LN, LD], Fraction[RN, RD]] {
      type Out = F
    }
  }

  /** Typeclass to determine if a fraction is non-zero and finite */
  trait Valid[F <: Fraction[_, _]]

  object Valid {
    implicit def valid[FN <: Singleton with Int, FD <: Singleton with Int](implicit ev0: Require[FN != 0], ev1: Require[FD != 0]): Valid[Fraction[FN, FD]] = new Valid[Fraction[FN, FD]] {}
  }
}

package libra
package ops

import spire.algebra._, spire.implicits._

object base {

  trait Show[A] {
    def apply(): String
  }
  object Show {
    def apply[A](s: String): Show[A] = new Show[A] {
      def apply(): String = s
    }
  }

  case class ConversionFactor[A, D, F <: Unit[D], T <: Unit[D]](val value: A)

  class Conversion[A, D, F <: Unit[D], T <: Unit[D]](val factor: A)

  object Conversion {

    implicit def conversionTo[A, D, F <: Unit[D], T <: Unit[D]](
      implicit to: ConversionFactor[A, D, F, T]
    ): Conversion[A, D, F, T] =
      new Conversion(to.value)

    implicit def conversionFrom[A, D, F <: Unit[D], T <: Unit[D]](
      implicit to: ConversionFactor[A, D, F, T],
      ev0: Refute[ConversionFactor[A, D, T, F]],
      ev1: MultiplicativeGroup[A]
    ): Conversion[A, D, T, F] =
      new Conversion(to.value.reciprocal)
  }
}

import shapeless._

import singleton.ops._

package object libra {

  trait Fraction[N <: XInt, D <: XInt]

  type Term[A, E <: Fraction[_, _]] = shapeless.labelled.FieldType[A, E]
  /** Represents a quantity with single dimension */
  type QuantityOf[A, T] = Quantity[A, OneOf[T] :: HNil]

  type OneOf[A] = Term[A, Fraction[1, 1]]

}

import shapeless._

package object libra {


  type Term[A, E <: Fraction[_, _]] = shapeless.labelled.FieldType[A, E]
  /** Represents a quantity with single dimension */
  type QuantityOf[A, T] = Quantity[A, OneOf[T] :: HNil]

  type OneOf[A] = Term[A, Fraction[1, 1]]

}

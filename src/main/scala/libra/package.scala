import shapeless._

package object libra {

  /** Represents a quantity with single dimension term and no tens */
  type OneOf[A, T] = Quantity[A, 0, Dimensions[T :: HNil, HNil]]


  /** Represents a quantity with single dimension term and E tens */
  type QuantityOf[A, E <: Singleton with Int, T] = Quantity[A, E, Dimensions[T :: HNil, HNil]]

}

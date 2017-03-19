import shapeless._

package object libra {


  /** Represents a quantity with single dimension */
  type QuantityOf[A, T] = Quantity[A, Dimensions[T :: HNil, HNil]]

}

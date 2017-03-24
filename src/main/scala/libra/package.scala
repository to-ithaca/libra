import shapeless._

import singleton.ops._

package object libra {

  /** Represents a fraction 
    * 
    * @tparam N the numerator
    * @tparam D the denominator
    */
  trait Fraction[N <: XInt, D <: XInt]

  /** Represents a single dimension in a HList of dimensions
    * 
    * @tparam A the base dimension e.g. Length
    * @tparam E the fractional exponent the dimension is raised to
    * 
    */
  type Term[A, E <: Fraction[_, _]] = shapeless.labelled.FieldType[A, E]

  /** Aliases a quantity with single dimension */
  type QuantityOf[A, T] = Quantity[A, OneOf[T] :: HNil]

  /** Aliases a single dimension */
  type OneOf[A] = Term[A, Fraction[1, 1]]
}

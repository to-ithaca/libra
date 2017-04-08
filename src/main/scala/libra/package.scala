import shapeless._

import singleton.ops._

package object libra {

  /** Represents a fraction 
    * 
    * @tparam N the numerator
    * @tparam D the denominator
    */
  trait Fraction[N <: XInt, D <: XInt]

  trait Unit[D]

  /** Represents a unit in a HList of units
    * 
    * @tparam D the base dimension e.g. Length
    * @tparam E the fractional exponent the dimension is raised to
    * @tparam U the unit
    * 
    */
  type Term[D, U <: Unit[_], E <: Fraction[_, _]] = shapeless.labelled.FieldType[D, (U, E)]

  type TermValue[U <: Unit[_], E <: Fraction[_, _]] = (U, E)

  /** Aliases a quantity with single unit */
  type QuantityOf[A, D, U <: Unit[D]] = Quantity[A, Term[D, U, Fraction[1, 1]] :: HNil]
}

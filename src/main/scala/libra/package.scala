import shapeless._

package object libra {

  /** Represents a unit in a HList of units
    * 
    * @tparam D the base dimension e.g. Length
    * @tparam E the fractional exponent the dimension is raised to
    * @tparam U the unit
    * 
    */
  type Term[D, U <: UnitOfMeasure[_], E <: Fraction[_, _]] = shapeless.labelled.FieldType[D, (U, E)]

  type TermValue[U <: UnitOfMeasure[_], E <: Fraction[_, _]] = (U, E)

  /** Aliases a quantity with single unit */
  type QuantityOf[A, D, U <: UnitOfMeasure[D]] = Quantity[A, Term[D, U, Fraction[1, 1]] :: HNil]
}

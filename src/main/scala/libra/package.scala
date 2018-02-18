import shapeless._

package object libra extends dimensions.All {

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


  /** Aliases to composite units */

  // They have to go somewhere, so may as well go here
  type VelocityQuantity[A, L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]

  type AccelerationQuantity[A, L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-2, 1]] :: HNil]

  type MomentumQuantity[A, M <: UnitOfMeasure[Mass], L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Mass, M, Fraction[1, 1]] :: Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]

  type ForceQuantity[A, M <: UnitOfMeasure[Mass], L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Mass, M, Fraction[1, 1]] :: Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-2, 1]] :: HNil]

  type AngularVelocityQuantity[A, L <: UnitOfMeasure[Angle], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Angle, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]
}

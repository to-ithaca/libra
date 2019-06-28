import shapeless.{ Witness => W, _ }

package object libra extends units.All {

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
  type QuantityOf[A, D, U <: UnitOfMeasure[D]] = Quantity[A, Term[D, U, Fraction[W.`1`.T, W.`1`.T]] :: HNil]


  /** Aliases to composite units */

  // They have to go somewhere, so may as well go here
  type VelocityQuantity[A, L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Length, L, Fraction[W.`1`.T, W.`1`.T]] :: Term[Time, T, Fraction[W.`-1`.T, W.`1`.T]] :: HNil]

  type AccelerationQuantity[A, L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Length, L, Fraction[W.`1`.T, W.`1`.T]] :: Term[Time, T, Fraction[W.`-2`.T, W.`1`.T]] :: HNil]

  type MomentumQuantity[A, M <: UnitOfMeasure[Mass], L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Mass, M, Fraction[W.`1`.T, W.`1`.T]] :: Term[Length, L, Fraction[W.`1`.T, W.`1`.T]] :: Term[Time, T, Fraction[W.`-1`.T, W.`1`.T]] :: HNil]

  type ForceQuantity[A, M <: UnitOfMeasure[Mass], L <: UnitOfMeasure[Length], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Mass, M, Fraction[W.`1`.T, W.`1`.T]] :: Term[Length, L, Fraction[W.`1`.T, W.`1`.T]] :: Term[Time, T, Fraction[W.`-2`.T, W.`1`.T]] :: HNil]

  type AngularVelocityQuantity[A, L <: UnitOfMeasure[Angle], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Angle, L, Fraction[W.`1`.T, W.`1`.T]] :: Term[Time, T, Fraction[W.`-1`.T, W.`1`.T]] :: HNil]
}

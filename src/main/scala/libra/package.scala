import shapeless._

package object libra extends AngleDimension {

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

  object degree extends AngleDegree
  object arcminute extends AngleArcminute
  object arcsecond extends AngleArcsecond
  object radian extends AngleRadian
  object gradian extends AngleGradian
  object turn extends AngleTurn

  object angle extends AngleDegree
      with AngleArcminute
      with AngleArcsecond
      with AngleRadian
      with AngleGradian
      with AngleTurn

  object degreesPerSecond extends AngularVelocityDegreesPerSecond
  object arcminutesPerSecond extends AngularVelocityArcminutesPerSecond
  object arcsecondsPerSecond extends AngularVelocityArcsecondsPerSecond
  object radiansPerSecond extends AngularVelocityRadiansPerSecond
  object gradiansPerSecond extends AngularVelocityGradiansPerSecond
  object turnsPerSecond extends AngularVelocityTurnsPerSecond

  object angularVelocity extends AngularVelocityDegreesPerSecond
      with AngularVelocityArcminutesPerSecond
      with AngularVelocityArcsecondsPerSecond
      with AngularVelocityRadiansPerSecond
      with AngularVelocityGradiansPerSecond
      with AngularVelocityTurnsPerSecond
}

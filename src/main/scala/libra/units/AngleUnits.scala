package libra
package units

trait AngleUnits {

  type Angle

  /** Angle units */
  trait Degree extends UnitOfMeasure[Angle]
  trait Arcminute extends UnitOfMeasure[Angle]
  trait Arcsecond extends UnitOfMeasure[Angle]
  trait Radian extends UnitOfMeasure[Angle]
  trait Gradian extends UnitOfMeasure[Angle]
  trait Turn extends UnitOfMeasure[Angle]

}

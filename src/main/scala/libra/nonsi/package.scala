package libra

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import libra.si.{Time, Second}
import shapeless._

/* Non-SI units */
package object nonsi {
  type Angle

  implicit def angleShow: Show[Angle] = Show[Angle]("∠")

  /** Angle units */
  trait Degree extends UnitOfMeasure[Angle]
  trait Arcminute extends UnitOfMeasure[Angle]
  trait Arcsecond extends UnitOfMeasure[Angle]

  implicit def degreeShow: Show[Degree] = Show[Degree]("degree")
  implicit def arcminuteShow: Show[Arcminute] = Show[Arcminute]("arcminute")
  implicit def arcsecondShow: Show[Arcsecond] = Show[Arcsecond]("arcsecond")

  implicit def degreeArcminuteConversion[A](
      implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Angle, Degree, Arcminute] =
    new ConversionFactor(c.fromInt(60))

  implicit def degreeArcsecondConversion[A](
      implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Angle, Degree, Arcsecond] =
    new ConversionFactor(c.fromInt(3600))

  implicit def arcminuteArcsecondConversion[A](
      implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Angle, Arcminute, Arcsecond] =
    new ConversionFactor(c.fromInt(60))

  type AngularVelocityQuantity[A, L <: libra.Unit[Angle], T <: libra.Unit[Time]] =
    Quantity[A, Term[Angle, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]

  implicit final class BaseQuantityNonSIOps[A](val a: A) extends AnyVal {
    def degree: QuantityOf[A, Angle, Degree] = Quantity(a)
    def arcminute: QuantityOf[A, Angle, Arcminute] = Quantity(a)
    def arcsecond: QuantityOf[A, Angle, Arcsecond] = Quantity(a)
    def degreessPerSecond: AngularVelocityQuantity[A, Degree, Second] = Quantity(a)
    def arcMinutesPerSecond: AngularVelocityQuantity[A, Arcminute, Second] = Quantity(a)
    def arcSecondsPerSecond: AngularVelocityQuantity[A, Arcsecond, Second] = Quantity(a)
  }
}

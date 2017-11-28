package libra

import libra.nonsi.Angle
import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import libra.si.{Second, Time}
import shapeless._

/* Non-SI units */
package object nonsi {
  type Angle

  implicit def angleShow: Show[Angle] = Show[Angle]("∠")

  /** Angle units */
  trait Degree extends UnitOfMeasure[Angle]
  trait Arcminute extends UnitOfMeasure[Angle]
  trait Arcsecond extends UnitOfMeasure[Angle]
  trait Radian extends UnitOfMeasure[Angle]

  implicit def degreeShow: Show[Degree] = Show[Degree]("degree")
  implicit def arcminuteShow: Show[Arcminute] = Show[Arcminute]("arcminute")
  implicit def arcsecondShow: Show[Arcsecond] = Show[Arcsecond]("arcsecond")
  implicit def radian: Show[Radian] = Show[Radian]("rad")

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

  implicit def radianDegreeConversion[A](
      implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Angle, Radian, Degree] =
    new ConversionFactor(c.fromDouble(180 / pi))

  implicit def radianArcminuteConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Radian, Arcminute] =
    degreeArcminuteConversion.compose(radianDegreeConversion)

  implicit def radianArcsecondConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Radian, Arcsecond] =
    degreeArcsecondConversion.compose(radianDegreeConversion)



  type AngularVelocityQuantity[A, L <: UnitOfMeasure[Angle], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Angle, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]

  implicit final class BaseQuantityNonSIOps[A](val a: A) extends AnyVal {
    def degree: QuantityOf[A, Angle, Degree] = Quantity(a)
    def arcminute: QuantityOf[A, Angle, Arcminute] = Quantity(a)
    def arcsecond: QuantityOf[A, Angle, Arcsecond] = Quantity(a)
    def degreessPerSecond: AngularVelocityQuantity[A, Degree, Second] = Quantity(a)
    def arcminutesPerSecond: AngularVelocityQuantity[A, Arcminute, Second] = Quantity(a)
    def arcsecondsPerSecond: AngularVelocityQuantity[A, Arcsecond, Second] = Quantity(a)
    def radian: QuantityOf[A, Angle, Radian] = Quantity(a)
  }
}

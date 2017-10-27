package libra

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import libra.si.Time

/* Non-SI units */
package object nonsi {
  type Angle

  implicit def angleShow: Show[Angle] = Show[Angle]("∠")

  /** Angle units */
  trait Degree extends Unit[Angle]
  trait Arcminute extends Unit[Angle]
  trait Arcsecond extends Unit[Angle]

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


  implicit final class BaseQuantityNonSIOps[A](val a: A) extends AnyVal {
    def degree: QuantityOf[A, Angle, Degree] = Quantity(a)
    def arcminute: QuantityOf[A, Angle, Arcminute] = Quantity(a)
    def arcsecond: QuantityOf[A, Angle, Arcsecond] = Quantity(a)
  }
}

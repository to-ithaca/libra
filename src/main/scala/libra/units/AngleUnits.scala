package libra
package units

import ops.base.{ConversionFactor, Show}
import spire.algebra._
import spire.math._

trait AngleUnits {

  type Angle

  implicit def angleShow: Show[Angle] = Show[Angle]("∠")

  /** Angle units */
  trait Degree extends UnitOfMeasure[Angle]
  trait Arcminute extends UnitOfMeasure[Angle]
  trait Arcsecond extends UnitOfMeasure[Angle]
  trait Radian extends UnitOfMeasure[Angle]
  trait Gradian extends UnitOfMeasure[Angle]
  trait Turn extends UnitOfMeasure[Angle]

  implicit def degreeShow: Show[Degree] = Show[Degree]("degree")
  implicit def arcminuteShow: Show[Arcminute] = Show[Arcminute]("arcminute")
  implicit def arcsecondShow: Show[Arcsecond] = Show[Arcsecond]("arcsecond")
  implicit def radianShow: Show[Radian] = Show[Radian]("rad")
  implicit def gradianShow: Show[Gradian] = Show[Gradian]("gon")
  implicit def turnShow: Show[Turn] = Show[Turn]("tr")

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
    new ConversionFactor(c.fromDouble(180.0 / pi))

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

  implicit def gradianRadianConversion[A](
      implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Angle, Gradian, Radian] =
    new ConversionFactor(c.fromDouble(pi / 200.0))

  implicit def gradianDegreeConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Gradian, Degree] =
    radianDegreeConversion.compose(gradianRadianConversion)

  implicit def gradianArcminuteConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Gradian, Arcminute] =
    radianArcminuteConversion.compose(gradianRadianConversion)

  implicit def gradianArcsecondConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Gradian, Arcsecond] =
    radianArcsecondConversion.compose(gradianRadianConversion)

  implicit def turnGradianConversion[A](
      implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Angle, Turn, Gradian] =
    new ConversionFactor(c.fromInt(400))

  implicit def turnRadianConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Turn, Radian] =
    gradianRadianConversion.compose(turnGradianConversion)

  implicit def turnDegreeConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Turn, Degree] =
    gradianDegreeConversion.compose(turnGradianConversion)

  implicit def turnArcminuteConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Turn, Arcminute] =
    gradianArcminuteConversion.compose(turnGradianConversion)

  implicit def turnArcsecondConversion[A](
      implicit c: ConvertableTo[A],
      multiplicative: MultiplicativeSemigroup[A]
  ): ConversionFactor[A, Angle, Turn, Arcsecond] =
    gradianArcsecondConversion.compose(turnGradianConversion)

}

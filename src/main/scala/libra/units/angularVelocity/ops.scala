package libra
package units
package angularVelocity

import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import libra.si.{Second, Time}
import shapeless._

trait AngularVelocityDimension {

  type AngularVelocityQuantity[A, L <: UnitOfMeasure[Angle], T <: UnitOfMeasure[Time]] =
    Quantity[A, Term[Angle, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]
}

trait AngularVelocityDegreesPerSecond extends AngularVelocityDimension {
  implicit final class AngleDegreesPerSecondOps[A](val a: A) {
    def degreesPerSecond: AngularVelocityQuantity[A, Degree, Second] = Quantity(a)
  }
}


trait AngularVelocityArcminutesPerSecond extends AngularVelocityDimension {
  implicit final class AngleArcminutesPerSecondOps[A](val a: A) {
    def arcminutesPerSecond: AngularVelocityQuantity[A, Arcminute, Second] = Quantity(a)
  }
}

trait AngularVelocityArcsecondsPerSecond extends AngularVelocityDimension {
  implicit final class AngleArcsecondsPerSecondOps[A](val a: A) {
    def arcsecondsPerSecond: AngularVelocityQuantity[A, Arcsecond, Second] = Quantity(a)
  }
}


trait AngularVelocityRadiansPerSecond extends AngularVelocityDimension {
  implicit final class AngleRadiansPerSecondOps[A](val a: A) {
    def radiansPerSecond: AngularVelocityQuantity[A, Radian, Second] = Quantity(a)
  }
}



trait AngularVelocityGradiansPerSecond extends AngularVelocityDimension {
  implicit final class AngleGradiansPerSecondOps[A](val a: A) {
    def gradiansPerSecond: AngularVelocityQuantity[A, Gradian, Second] = Quantity(a)
  }
}


trait AngularVelocityTurnsPerSecond extends AngularVelocityDimension {
  implicit final class AngleTurnsPerSecondOps[A](val a: A) {
    def turnsPerSecond: AngularVelocityQuantity[A, Turn, Second] = Quantity(a)
  }
}

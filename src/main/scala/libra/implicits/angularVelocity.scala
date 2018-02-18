package libra
package implicits

import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import shapeless._

trait AngularVelocityDegreesPerSecondImplicits {
  implicit final class AngularVelocityDegreesPerSecondOps[A](val a: A) {
    def degreesPerSecond: AngularVelocityQuantity[A, Degree, Second] = Quantity(a)
  }
}


trait AngularVelocityArcminutesPerSecondImplicits {
  implicit final class AngularVelocityArcminutesPerSecondOps[A](val a: A) {
    def arcminutesPerSecond: AngularVelocityQuantity[A, Arcminute, Second] = Quantity(a)
  }
}

trait AngularVelocityArcsecondsPerSecondImplicits {
  implicit final class AngularVelocityArcsecondsPerSecondOps[A](val a: A) {
    def arcsecondsPerSecond: AngularVelocityQuantity[A, Arcsecond, Second] = Quantity(a)
  }
}


trait AngularVelocityRadiansPerSecondImplicits {
  implicit final class AngularVelocityRadiansPerSecondOps[A](val a: A) {
    def radiansPerSecond: AngularVelocityQuantity[A, Radian, Second] = Quantity(a)
  }
}



trait AngularVelocityGradiansPerSecondImplicits {
  implicit final class AngularVelocityGradiansPerSecondOps[A](val a: A) {
    def gradiansPerSecond: AngularVelocityQuantity[A, Gradian, Second] = Quantity(a)
  }
}


trait AngularVelocityTurnsPerSecondImplicits {
  implicit final class AngularVelocityTurnsPerSecondOps[A](val a: A) {
    def turnsPerSecond: AngularVelocityQuantity[A, Turn, Second] = Quantity(a)
  }
}

trait AngularVelocityImplicits extends AngularVelocityDegreesPerSecondImplicits
    with AngularVelocityArcminutesPerSecondImplicits
    with AngularVelocityArcsecondsPerSecondImplicits
    with AngularVelocityRadiansPerSecondImplicits
    with AngularVelocityGradiansPerSecondImplicits
    with AngularVelocityTurnsPerSecondImplicits

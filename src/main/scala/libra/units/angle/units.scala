package libra
package units
package angle

import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import libra.si.{Second, Time}
import shapeless._

trait AngleDegree {
  implicit final class AngleDegreeOps[A](val a: A) {
    def degree: QuantityOf[A, Angle, Degree] = Quantity(a)
  }
}

trait AngleArcminute {
  implicit final class AngleArcminuteOps[A](val a: A) {
    def arcminute: QuantityOf[A, Angle, Arcminute] = Quantity(a)
  }
}

trait AngleArcsecond {
  implicit final class ArcsecondOps[A](val a: A) {
    def arcsecond: QuantityOf[A, Angle, Arcsecond] = Quantity(a)
  }
}


trait AngleRadian {
  implicit final class RadianOps[A](val a: A) {
    def radian: QuantityOf[A, Angle, Radian] = Quantity(a)
  }
}

trait AngleGradian {
  implicit final class GradianOps[A](val a: A) {
    def gradian: QuantityOf[A, Angle, Gradian] = Quantity(a)
  }
}

trait AngleTurn {
  implicit final class TurnOps[A](val a: A) {
    def turn: QuantityOf[A, Angle, Turn] = Quantity(a)
  }
}

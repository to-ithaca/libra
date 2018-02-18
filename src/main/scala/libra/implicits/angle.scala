package libra
package implicits

import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import libra.si.{Second, Time}
import shapeless._

trait AngleDegreeImplicits {
  implicit final class AngleDegreeOps[A](val a: A) {
    def degree: QuantityOf[A, Angle, Degree] = Quantity(a)
  }
}

trait AngleArcminuteImplicits {
  implicit final class AngleArcminuteOps[A](val a: A) {
    def arcminute: QuantityOf[A, Angle, Arcminute] = Quantity(a)
  }
}

trait AngleArcsecondImplicits {
  implicit final class AngleArcsecondOps[A](val a: A) {
    def arcsecond: QuantityOf[A, Angle, Arcsecond] = Quantity(a)
  }
}


trait AngleRadianImplicits {
  implicit final class AngleRadianOps[A](val a: A) {
    def radian: QuantityOf[A, Angle, Radian] = Quantity(a)
  }
}

trait AngleGradianImplicits {
  implicit final class AngleGradianOps[A](val a: A) {
    def gradian: QuantityOf[A, Angle, Gradian] = Quantity(a)
  }
}

trait AngleTurnImplicits {
  implicit final class AngleTurnOps[A](val a: A) {
    def turn: QuantityOf[A, Angle, Turn] = Quantity(a)
  }
}

trait AngleImplicits extends AngleDegreeImplicits
    with AngleArcminuteImplicits
    with AngleArcsecondImplicits
    with AngleRadianImplicits
    with AngleGradianImplicits
    with AngleTurnImplicits

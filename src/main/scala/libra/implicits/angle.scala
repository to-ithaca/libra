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

trait AngleImplicits {
  implicit final class AngleOps[A](val a: A) {
    def degree: QuantityOf[A, Angle, Degree] = Quantity(a)
    def arcminute: QuantityOf[A, Angle, Arcminute] = Quantity(a)
    def arcsecond: QuantityOf[A, Angle, Arcsecond] = Quantity(a)
    def radian: QuantityOf[A, Angle, Radian] = Quantity(a)
    def gradian: QuantityOf[A, Angle, Gradian] = Quantity(a)
    def turn: QuantityOf[A, Angle, Turn] = Quantity(a)
  }
}

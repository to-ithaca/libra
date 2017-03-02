package libra
package syntax

import shapeless._
import libra.baseDimensions._

final class BaseQuantityOps[A](val a: A) extends AnyVal {
  def m: Quantity[A, 0, Dimensions[Length :: HNil, HNil]] = Quantity(a)
  def kg: Quantity[A, 0, Dimensions[Mass :: HNil, HNil]] = Quantity(a)
  def s: Quantity[A, 0, Dimensions[Time :: HNil, HNil]] = Quantity(a)
}

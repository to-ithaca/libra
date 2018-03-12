package libra
package implicits

import units.MetricUnit
import ops.base.Show

import singleton.ops.{Length => _, _}

trait LengthImplicits {

  implicit def lengthShow: Show[Length] = Show[Length]("L")

  implicit def metricLengthShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Length]] = Show(s"${s()}m")

  implicit final class LengthOps[A](val a: A) {
    def km: QuantityOf[A, Length, Kilometre] = Quantity(a)
    def m: QuantityOf[A, Length, Metre] = Quantity(a)
    def dm: QuantityOf[A, Length, Decimetre] = Quantity(a)
    def cm: QuantityOf[A, Length, Centimetre] = Quantity(a)
    def mm: QuantityOf[A, Length, Millimetre] = Quantity(a)
    def μm: QuantityOf[A, Length, Micrometre] = Quantity(a)
    def nm: QuantityOf[A, Length, Nanometre] = Quantity(a)
    def pm: QuantityOf[A, Length, Picometre] = Quantity(a)
    def fm: QuantityOf[A, Length, Femtometre] = Quantity(a)
    def am: QuantityOf[A, Length, Attometre] = Quantity(a)
  }
}

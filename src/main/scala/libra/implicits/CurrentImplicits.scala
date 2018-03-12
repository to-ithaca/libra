package libra
package implicits

import units.MetricUnit
import ops.base.Show

import singleton.ops._

trait CurrentImplicits {

  implicit def currentShow: Show[Current] = Show[Current]("I")

  implicit def metricCurrentShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Current]] = Show(s"${s()}A")

  implicit final class CurrentOps[A](val a: A) {
    def kA: QuantityOf[A, Current, Kiloampere] = Quantity(a)
    def A: QuantityOf[A, Current, Ampere] = Quantity(a)
    def dA: QuantityOf[A, Current, Deciampere] = Quantity(a)
    def cA: QuantityOf[A, Current, Centiampere] = Quantity(a)
    def mA: QuantityOf[A, Current, Milliampere] = Quantity(a)
    def μA: QuantityOf[A, Current, Microampere] = Quantity(a)
    def nA: QuantityOf[A, Current, Nanoampere] = Quantity(a)
    def pA: QuantityOf[A, Current, Picoampere] = Quantity(a)
    def fA: QuantityOf[A, Current, Femtoampere] = Quantity(a)
  }
}

package libra
package implicits

import units.MetricUnit
import ops.base.Show

import singleton.ops._

trait TemperatureImplicits {

  implicit def temperatureShow: Show[Temperature] = Show[Temperature]("θ")

  implicit def metricTemperatureShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Temperature]] = Show(s"${s()}K")

  implicit final class TemperatureOps[A](val a: A) {
    def kK: QuantityOf[A, Temperature, Kilokelvin] = Quantity(a)
    def K: QuantityOf[A, Temperature, Kelvin] = Quantity(a)
    def dK: QuantityOf[A, Temperature, Decikelvin] = Quantity(a)
    def cK: QuantityOf[A, Temperature, Centikelvin] = Quantity(a)
    def mK: QuantityOf[A, Temperature, Millikelvin] = Quantity(a)
    def μK: QuantityOf[A, Temperature, Microkelvin] = Quantity(a)
    def nK: QuantityOf[A, Temperature, Nanokelvin] = Quantity(a)
    def pK: QuantityOf[A, Temperature, Picokelvin] = Quantity(a)
    def fK: QuantityOf[A, Temperature, Femtokelvin] = Quantity(a)
    def aK: QuantityOf[A, Temperature, Attokelvin] = Quantity(a)
  }
}

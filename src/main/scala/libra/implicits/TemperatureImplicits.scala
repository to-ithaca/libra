package libra
package implicits

trait TemperatureImplicits {

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

package libra
package implicits

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait TemperatureKilokelvinImplicits {
  implicit final class TemperatureKilokelvinOps[A](val a: A) {
     def kK: QuantityOf[A, Temperature, Kilokelvin] = Quantity(a)
  }
}

trait TemperatureKelvinImplicits {
  implicit final class TemperatureKelvinOps[A](val a: A) {
     def K: QuantityOf[A, Temperature, Kelvin] = Quantity(a)
  }
}

trait TemperatureDecikelvinImplicits {
  implicit final class TemperatureDecikelvinOps[A](val a: A) {
     def dK: QuantityOf[A, Temperature, Decikelvin] = Quantity(a)
  }
}

trait TemperatureCentikelvinImplicits {
  implicit final class TemperatureCentikelvinOps[A](val a: A) {
     def cK: QuantityOf[A, Temperature, Centikelvin] = Quantity(a)
  }
}

trait TemperatureMillikelvinImplicits {
  implicit final class TemperatureMillikelvinOps[A](val a: A) {
     def mK: QuantityOf[A, Temperature, Millikelvin] = Quantity(a)
  }
}

trait TemperatureMicrokelvinImplicits {
  implicit final class TemperatureMicrokelvinOps[A](val a: A) {
    def μK: QuantityOf[A, Temperature, Microkelvin] = Quantity(a)
  }
}

trait TemperatureNanokelvinImplicits {
  implicit final class TemperatureNanokelvinOps[A](val a: A) {
     def nK: QuantityOf[A, Temperature, Nanokelvin] = Quantity(a)
  }
}

trait TemperaturePicokelvinImplicits {
  implicit final class TemperaturePicokelvinOps[A](val a: A) {
     def pK: QuantityOf[A, Temperature, Picokelvin] = Quantity(a)
  }
}

trait TemperatureFemtokelvinImplicits {
  implicit final class TemperatureFemtokelvinOps[A](val a: A) {
     def fK: QuantityOf[A, Temperature, Femtokelvin] = Quantity(a)
  }
}

trait TemperatureAttokelvinImplicits {
  implicit final class TemperatureAttokelvinOps[A](val a: A) {
     def fK: QuantityOf[A, Temperature, Attokelvin] = Quantity(a)
  }
}

trait TemperatureImplicits extends TemperatureKilokelvinImplicits
    with TemperatureKelvinImplicits
    with TemperatureDecikelvinImplicits
    with TemperatureCentikelvinImplicits
    with TemperatureMillikelvinImplicits
    with TemperatureMicrokelvinImplicits
    with TemperatureNanokelvinImplicits
    with TemperaturePicokelvinImplicits
    with TemperatureFemtokelvinImplicits

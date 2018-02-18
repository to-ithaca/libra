package libra
package implicits

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait CurrentKiloampereImplicits {
  implicit final class CurrentKiloampereOps[A](val a: A) {
     def kA: QuantityOf[A, Current, Kiloampere] = Quantity(a)
  }
}

trait CurrentAmpereImplicits {
  implicit final class CurrentAmpereOps[A](val a: A) {
     def A: QuantityOf[A, Current, Ampere] = Quantity(a)
  }
}

trait CurrentDeciampereImplicits {
  implicit final class CurrentDeciampereOps[A](val a: A) {
     def dA: QuantityOf[A, Current, Deciampere] = Quantity(a)
  }
}

trait CurrentCentiampereImplicits {
  implicit final class CurrentCentiampereOps[A](val a: A) {
     def cA: QuantityOf[A, Current, Centiampere] = Quantity(a)
  }
}

trait CurrentMilliampereImplicits {
  implicit final class CurrentMilliampereOps[A](val a: A) {
     def mA: QuantityOf[A, Current, Milliampere] = Quantity(a)
  }
}

trait CurrentMicroampereImplicits {
  implicit final class CurrentMicroampereOps[A](val a: A) {
    def μA: QuantityOf[A, Current, Microampere] = Quantity(a)
  }
}

trait CurrentNanoampereImplicits {
  implicit final class CurrentNanoampereOps[A](val a: A) {
     def nA: QuantityOf[A, Current, Nanoampere] = Quantity(a)
  }
}

trait CurrentPicoampereImplicits {
  implicit final class CurrentPicoampereOps[A](val a: A) {
     def pA: QuantityOf[A, Current, Picoampere] = Quantity(a)
  }
}

trait CurrentFemtoampereImplicits {
  implicit final class CurrentFemtoampereOps[A](val a: A) {
     def fA: QuantityOf[A, Current, Femtoampere] = Quantity(a)
  }
}

trait CurrentAttoampereImplicits {
  implicit final class CurrentAttoampereOps[A](val a: A) {
     def fA: QuantityOf[A, Current, Attoampere] = Quantity(a)
  }
}

trait CurrentImplicits extends CurrentKiloampereImplicits
    with CurrentAmpereImplicits
    with CurrentDeciampereImplicits
    with CurrentCentiampereImplicits
    with CurrentMilliampereImplicits
    with CurrentMicroampereImplicits
    with CurrentNanoampereImplicits
    with CurrentPicoampereImplicits
    with CurrentFemtoampereImplicits

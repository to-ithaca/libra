package libra
package implicits

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait LengthKilometreImplicits {
  implicit final class LengthKilometreOps[A](val a: A) {
     def km: QuantityOf[A, Length, Kilometre] = Quantity(a)
  }
}

trait LengthMetreImplicits {
  implicit final class LengthMetreOps[A](val a: A) {
     def m: QuantityOf[A, Length, Metre] = Quantity(a)
  }
}

trait LengthDecimetreImplicits {
  implicit final class LengthDecimetreOps[A](val a: A) {
     def dm: QuantityOf[A, Length, Decimetre] = Quantity(a)
  }
}

trait LengthCentimetreImplicits {
  implicit final class LengthCentimetreOps[A](val a: A) {
     def cm: QuantityOf[A, Length, Centimetre] = Quantity(a)
  }
}

trait LengthMillimetreImplicits {
  implicit final class LengthMillimetreOps[A](val a: A) {
     def mm: QuantityOf[A, Length, Millimetre] = Quantity(a)
  }
}

trait LengthMicrometreImplicits {
  implicit final class LengthMicrometreOps[A](val a: A) {
    def μm: QuantityOf[A, Length, Micrometre] = Quantity(a)
  }
}

trait LengthNanometreImplicits {
  implicit final class LengthNanometreOps[A](val a: A) {
     def nm: QuantityOf[A, Length, Nanometre] = Quantity(a)
  }
}

trait LengthPicometreImplicits {
  implicit final class LengthPicometreOps[A](val a: A) {
     def pm: QuantityOf[A, Length, Picometre] = Quantity(a)
  }
}

trait LengthFemtometreImplicits {
  implicit final class LengthFemtometreOps[A](val a: A) {
     def fm: QuantityOf[A, Length, Femtometre] = Quantity(a)
  }
}

trait LengthAttometreImplicits {
  implicit final class LengthAttometreOps[A](val a: A) {
     def fm: QuantityOf[A, Length, Attometre] = Quantity(a)
  }
}

trait LengthImplicits extends LengthKilometreImplicits
    with LengthMetreImplicits
    with LengthDecimetreImplicits
    with LengthCentimetreImplicits
    with LengthMillimetreImplicits
    with LengthMicrometreImplicits
    with LengthNanometreImplicits
    with LengthPicometreImplicits
    with LengthFemtometreImplicits

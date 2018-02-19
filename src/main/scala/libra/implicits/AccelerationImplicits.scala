package libra
package implicits

trait AccelerationImplicits {

  implicit final class AccelerationOps[A](val a: A) {
    def kmps2: AccelerationQuantity[A, Kilometre, Second] = Quantity(a)
    def mps2: AccelerationQuantity[A, Metre, Second] = Quantity(a)
    def dmps2: AccelerationQuantity[A, Decimetre, Second] = Quantity(a)
    def cmps2: AccelerationQuantity[A, Centimetre, Second] = Quantity(a)
    def mmps2: AccelerationQuantity[A, Millimetre, Second] = Quantity(a)
    def μmps2: AccelerationQuantity[A, Micrometre, Second] = Quantity(a)
    def nmps2: AccelerationQuantity[A, Nanometre, Second] = Quantity(a)
    def pmps2: AccelerationQuantity[A, Picometre, Second] = Quantity(a)
    def fmps2: AccelerationQuantity[A, Femtometre, Second] = Quantity(a)
    def amps2: AccelerationQuantity[A, Attometre, Second] = Quantity(a)
  }
}

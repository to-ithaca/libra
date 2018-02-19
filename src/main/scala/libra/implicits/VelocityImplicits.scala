package libra
package implicits


trait VelocityImplicits {

  implicit final class SIVelocityQuantityOps[A](val a: A) {
    def kmps: VelocityQuantity[A, Kilometre, Second] = Quantity(a)
    def mps: VelocityQuantity[A, Metre, Second] = Quantity(a)
    def dmps: VelocityQuantity[A, Decimetre, Second] = Quantity(a)
    def cmps: VelocityQuantity[A, Centimetre, Second] = Quantity(a)
    def mmps: VelocityQuantity[A, Millimetre, Second] = Quantity(a)
    def μmps: VelocityQuantity[A, Micrometre, Second] = Quantity(a)
    def nmps: VelocityQuantity[A, Nanometre, Second] = Quantity(a)
    def pmps: VelocityQuantity[A, Picometre, Second] = Quantity(a)
    def fmps: VelocityQuantity[A, Femtometre, Second] = Quantity(a)
    def amps: VelocityQuantity[A, Attometre, Second] = Quantity(a)
  }
}

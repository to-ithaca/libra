package libra

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

/** SI units */
package object si {

  implicit final class SIVelocityQuantityOps[A](val a: A) extends AnyVal {
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

  implicit final class SIAccellerationQuantityOps[A](val a: A) extends AnyVal {
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

  implicit final class MomentumQuantityOps[A](val a: A) extends AnyVal {
    def kgmps: MomentumQuantity[A, Kilogram, Metre, Second] = Quantity(a)
    def Ns: MomentumQuantity[A, Kilogram, Metre, Second] = Quantity(a)
  }

  implicit final class ForceQuantityOps[A](val a: A) extends AnyVal {
    def N: ForceQuantity[A, Kilogram, Metre, Second] = Quantity(a)
  }
}

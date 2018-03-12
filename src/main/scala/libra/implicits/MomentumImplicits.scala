package libra
package implicits

trait MomentumImplicits {

  implicit final class MomentumQuantityOps[A](val a: A) {
    def kgmps: MomentumQuantity[A, Kilogram, Metre, Second] = Quantity(a)
    def Ns: MomentumQuantity[A, Kilogram, Metre, Second] = Quantity(a)
  }
}

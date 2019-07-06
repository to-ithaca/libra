package libra
package implicits

trait ForceImplicits {

  implicit final class ForceQuantityOps[A](val a: A) {
    def N: ForceQuantity[A, Kilogram, Metre, Second] = Quantity(a)
  }
}

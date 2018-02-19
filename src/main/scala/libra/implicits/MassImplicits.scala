package libra
package implicits

trait MassImplicits {

  implicit final class MassOps[A](val a: A) {
    def kg: QuantityOf[A, Mass, Kilogram] = Quantity(a)
    def g: QuantityOf[A, Mass, Gram] = Quantity(a)
    def dg: QuantityOf[A, Mass, Decigram] = Quantity(a)
    def cg: QuantityOf[A, Mass, Centigram] = Quantity(a)
    def mg: QuantityOf[A, Mass, Milligram] = Quantity(a)
    def μg: QuantityOf[A, Mass, Microgram] = Quantity(a)
    def ng: QuantityOf[A, Mass, Nanogram] = Quantity(a)
    def pg: QuantityOf[A, Mass, Picogram] = Quantity(a)
    def fg: QuantityOf[A, Mass, Femtogram] = Quantity(a)
    def ag: QuantityOf[A, Mass, Attogram] = Quantity(a)
  }
}

package libra

case class Quantity[A, I <: Singleton with Int, D <: Dimensions[_, _]](value: A)

object Quantity {
  import syntax.QuantityOps
  implicit def quantityOps[A, P <: Singleton with Int, D <: Dimensions[_, _]](q: Quantity[A, P, D]): QuantityOps[A, P, D] = new QuantityOps(q)
}

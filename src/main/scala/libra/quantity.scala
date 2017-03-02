package libra

//TODO: make this parameterized on any type
case class Quantity[I <: Singleton with Int, D <: Dimensions[_, _]](value: Double)

object Quantity {
  import syntax.QuantityOps
  implicit def quantityOps[P <: Singleton with Int, D <: Dimensions[_, _]](q: Quantity[P, D]): QuantityOps[P, D] = new QuantityOps(q)
}

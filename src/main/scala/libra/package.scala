
package object libra {
  implicit def toBaseQuantityOps[A](a: A): syntax.BaseQuantityOps[A] = new syntax.BaseQuantityOps(a)
}

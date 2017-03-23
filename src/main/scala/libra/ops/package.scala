package libra

package object ops {
  /** Evidence that an implicit A is not present */
  trait Refute[A]

  object Refute {
    implicit def ambiguous1[A](implicit ev: A): Refute[A] = new Refute[A] {}
    implicit def ambiguous2[A](implicit ev: A): Refute[A] = new Refute[A] {}
    implicit def refute[A]: Refute[A] = new Refute[A] {}
  }
}

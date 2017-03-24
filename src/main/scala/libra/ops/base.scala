package libra
package ops

object base {

  trait Show[A] {
    def apply(): String
  }
  object Show {
    def apply[A](s: String): Show[A] = new Show[A] {
      def apply(): String = s
    }
  }

  trait Conversion[A, D, UL <: Unit[D], UR <: Unit[D]] {
    def from(l: A): A
    def to(r: A): A
  }
}


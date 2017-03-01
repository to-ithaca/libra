package libra
package ops

object base {

  trait Show[A] {
    def apply(): String
  }

  trait ShowDimension[A] extends Show[A]

  object ShowDimension {
    def apply[A](s: String): ShowDimension[A] = new ShowDimension[A] {
      def apply(): String = s
    }
  }

  trait ShowUnit[A] extends Show[A]

  object ShowUnit {
    def apply[A](s: String): ShowUnit[A] = new ShowUnit[A] {
      def apply(): String = s
    }
  }
}

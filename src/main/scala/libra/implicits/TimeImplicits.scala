package libra
package implicits


trait TimeImplicits {

  implicit final class TimeOps[A](val a: A) {
    def ks: QuantityOf[A, Time, Kilosecond] = Quantity(a)
    def s: QuantityOf[A, Time, Second] = Quantity(a)
    def ds: QuantityOf[A, Time, Decisecond] = Quantity(a)
    def cs: QuantityOf[A, Time, Centisecond] = Quantity(a)
    def ms: QuantityOf[A, Time, Millisecond] = Quantity(a)
    def μs: QuantityOf[A, Time, Microsecond] = Quantity(a)
    def ns: QuantityOf[A, Time, Nanosecond] = Quantity(a)
    def ps: QuantityOf[A, Time, Picosecond] = Quantity(a)
    def fs: QuantityOf[A, Time, Femtosecond] = Quantity(a)
    def as: QuantityOf[A, Time, Attosecond] = Quantity(a)
    def days: QuantityOf[A, Time, Day] = Quantity(a)
    def hours: QuantityOf[A, Time, Hour] = Quantity(a)
  }
}

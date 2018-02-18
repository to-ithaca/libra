package libra
package implicits

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait TimeKilosecondImplicits {
  implicit final class TimeKilosecondOps[A](val a: A) {
     def ks: QuantityOf[A, Time, Kilosecond] = Quantity(a)
  }
}

trait TimeSecondImplicits {
  implicit final class TimeSecondOps[A](val a: A) {
     def s: QuantityOf[A, Time, Second] = Quantity(a)
  }
}

trait TimeDecisecondImplicits {
  implicit final class TimeDecisecondOps[A](val a: A) {
     def ds: QuantityOf[A, Time, Decisecond] = Quantity(a)
  }
}

trait TimeCentisecondImplicits {
  implicit final class TimeCentisecondOps[A](val a: A) {
     def cs: QuantityOf[A, Time, Centisecond] = Quantity(a)
  }
}

trait TimeMillisecondImplicits {
  implicit final class TimeMillisecondOps[A](val a: A) {
     def ms: QuantityOf[A, Time, Millisecond] = Quantity(a)
  }
}

trait TimeMicrosecondImplicits {
  implicit final class TimeMicrosecondOps[A](val a: A) {
    def μs: QuantityOf[A, Time, Microsecond] = Quantity(a)
  }
}

trait TimeNanosecondImplicits {
  implicit final class TimeNanosecondOps[A](val a: A) {
     def ns: QuantityOf[A, Time, Nanosecond] = Quantity(a)
  }
}

trait TimePicosecondImplicits {
  implicit final class TimePicosecondOps[A](val a: A) {
     def ps: QuantityOf[A, Time, Picosecond] = Quantity(a)
  }
}

trait TimeFemtosecondImplicits {
  implicit final class TimeFemtosecondOps[A](val a: A) {
     def fs: QuantityOf[A, Time, Femtosecond] = Quantity(a)
  }
}

trait TimeAttosecondImplicits {
  implicit final class TimeAttosecondOps[A](val a: A) {
     def fs: QuantityOf[A, Time, Attosecond] = Quantity(a)
  }
}

trait TimeImplicits extends TimeKilosecondImplicits
    with TimeSecondImplicits
    with TimeDecisecondImplicits
    with TimeCentisecondImplicits
    with TimeMillisecondImplicits
    with TimeMicrosecondImplicits
    with TimeNanosecondImplicits
    with TimePicosecondImplicits
    with TimeFemtosecondImplicits

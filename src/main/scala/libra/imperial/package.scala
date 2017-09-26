package libra

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import libra.si.Time

/* Imperial units */
package object imperial {

  /** Time units */
  trait Day extends Unit[Time]
  trait Hour extends Unit[Time]

  implicit def dayShow: Show[Day] = Show[Day]("days")
  implicit def hourShow: Show[Hour] = Show[Hour]("hours")

  implicit def dayHourConversion[A](
    implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Time, Day, Hour] =
    new ConversionFactor(c.fromInt(24))

  implicit final class BaseQuantityImperialOps[A](val a: A) extends AnyVal {
    def days: QuantityOf[A, Time, Day] = Quantity(a)
    def hours: QuantityOf[A, Time, Hour] = Quantity(a)
  }
}

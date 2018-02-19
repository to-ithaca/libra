package libra
package units

import ops.base.{Show, ConversionFactor}
import spire.math._
import singleton.ops._

trait TimeUnits extends MetricUnitImplicits {

  type Time

  type Kilosecond = MetricUnit[3, Time]
  type Second = MetricUnit[0, Time]
  type Decisecond = MetricUnit[-1, Time]
  type Centisecond = MetricUnit[-2, Time]
  type Millisecond = MetricUnit[-3, Time]
  type Microsecond = MetricUnit[-6, Time]
  type Nanosecond = MetricUnit[-9, Time]
  type Picosecond = MetricUnit[-12, Time]
  type Femtosecond = MetricUnit[-15, Time]
  type Attosecond = MetricUnit[-18, Time]

  trait Day extends UnitOfMeasure[Time]
  trait Hour extends UnitOfMeasure[Time]

  implicit def timeShow: Show[Time] = Show[Time]("T")

  implicit def metricTimeShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Time]] = Show(s"${s()}s")


  implicit def dayShow: Show[Day] = Show[Day]("days")
  implicit def hourShow: Show[Hour] = Show[Hour]("hours")

  implicit def dayHourConversion[A](
    implicit c: ConvertableTo[A]
  ): ConversionFactor[A, Time, Day, Hour] =
    new ConversionFactor(c.fromInt(24))
}

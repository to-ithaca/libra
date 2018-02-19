package libra
package units

import ops.base.Show
import singleton.ops._

trait CurrentUnits extends MetricUnitImplicits {

  type Current

  type Kiloampere = MetricUnit[3, Current]
  type Ampere = MetricUnit[0, Current]
  type Deciampere = MetricUnit[-1, Current]
  type Centiampere = MetricUnit[-2, Current]
  type Milliampere = MetricUnit[-3, Current]
  type Microampere = MetricUnit[-6, Current]
  type Nanoampere = MetricUnit[-9, Current]
  type Picoampere = MetricUnit[-12, Current]
  type Femtoampere = MetricUnit[-15, Current]
  type Attoampere = MetricUnit[-18, Current]

  implicit def currentShow: Show[Current] = Show[Current]("I")

  implicit def metricCurrentShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Current]] = Show(s"${s()}A")
}

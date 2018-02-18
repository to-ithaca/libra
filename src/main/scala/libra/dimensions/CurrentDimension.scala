package libra
package dimensions

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait CurrentDimension extends MetricUnitImplicits {

  type Current

  type Kiloampere = MetricUnit[3, Time]
  type Ampere = MetricUnit[0, Time]
  type Deciampere = MetricUnit[-1, Time]
  type Centiampere = MetricUnit[-2, Time]
  type Milliampere = MetricUnit[-3, Time]
  type Microampere = MetricUnit[-6, Time]
  type Nanoampere = MetricUnit[-9, Time]
  type Picoampere = MetricUnit[-12, Time]
  type Femtoampere = MetricUnit[-15, Time]
  type Attoampere = MetricUnit[-18, Time]

  implicit def currentShow: Show[Current] = Show[Current]("I")

  implicit def metricCurrentShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Current]] = Show(s"${s()}A")
}

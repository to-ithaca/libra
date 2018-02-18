package libra
package dimensions

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait TemperatureDimension extends MetricUnitImplicits {

  type Temperature

  type Kilokelvin = MetricUnit[3, Time]
  type Kelvin = MetricUnit[0, Time]
  type Decikelvin = MetricUnit[-1, Time]
  type Centikelvin = MetricUnit[-2, Time]
  type Millikelvin = MetricUnit[-3, Time]
  type Microkelvin = MetricUnit[-6, Time]
  type Nanokelvin = MetricUnit[-9, Time]
  type Picokelvin = MetricUnit[-12, Time]
  type Femtokelvin = MetricUnit[-15, Time]
  type Attokelvin = MetricUnit[-18, Time]

  implicit def temperatureShow: Show[Temperature] = Show[Temperature]("θ")

  implicit def metricTemperatureShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Temperature]] = Show(s"${s()}K")
}

package libra
package units

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait TemperatureUnits extends MetricUnitImplicits {

  type Temperature

  type Kilokelvin = MetricUnit[3, Temperature]
  type Kelvin = MetricUnit[0, Temperature]
  type Decikelvin = MetricUnit[-1, Temperature]
  type Centikelvin = MetricUnit[-2, Temperature]
  type Millikelvin = MetricUnit[-3, Temperature]
  type Microkelvin = MetricUnit[-6, Temperature]
  type Nanokelvin = MetricUnit[-9, Temperature]
  type Picokelvin = MetricUnit[-12, Temperature]
  type Femtokelvin = MetricUnit[-15, Temperature]
  type Attokelvin = MetricUnit[-18, Temperature]

  implicit def temperatureShow: Show[Temperature] = Show[Temperature]("θ")

  implicit def metricTemperatureShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Temperature]] = Show(s"${s()}K")
}

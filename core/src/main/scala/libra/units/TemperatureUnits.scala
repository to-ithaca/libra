package libra
package units

import shapeless.{ Witness => W }

trait TemperatureUnits {

  type Temperature

  type Kilokelvin  = MetricUnit[W.`  3`.T, Temperature]
  type Kelvin      = MetricUnit[W.`  0`.T, Temperature]
  type Decikelvin  = MetricUnit[W.` -1`.T, Temperature]
  type Centikelvin = MetricUnit[W.` -2`.T, Temperature]
  type Millikelvin = MetricUnit[W.` -3`.T, Temperature]
  type Microkelvin = MetricUnit[W.` -6`.T, Temperature]
  type Nanokelvin  = MetricUnit[W.` -9`.T, Temperature]
  type Picokelvin  = MetricUnit[W.`-12`.T, Temperature]
  type Femtokelvin = MetricUnit[W.`-15`.T, Temperature]
  type Attokelvin  = MetricUnit[W.`-18`.T, Temperature]

}

package libra
package units

import shapeless.{ Witness => W }

trait CurrentUnits {

  type Current

  type Kiloampere  = MetricUnit[W.`  3`.T, Current]
  type Ampere      = MetricUnit[W.`  0`.T, Current]
  type Deciampere  = MetricUnit[W.` -1`.T, Current]
  type Centiampere = MetricUnit[W.` -2`.T, Current]
  type Milliampere = MetricUnit[W.` -3`.T, Current]
  type Microampere = MetricUnit[W.` -6`.T, Current]
  type Nanoampere  = MetricUnit[W.` -9`.T, Current]
  type Picoampere  = MetricUnit[W.`-12`.T, Current]
  type Femtoampere = MetricUnit[W.`-15`.T, Current]
  type Attoampere  = MetricUnit[W.`-18`.T, Current]

}

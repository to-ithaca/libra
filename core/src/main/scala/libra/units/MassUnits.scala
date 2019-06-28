package libra
package units

import shapeless.{ Witness => W }

trait MassUnits {

  type Mass

  type Kilogram  = MetricUnit[W.`  3`.T, Mass]
  type Gram      = MetricUnit[W.`  0`.T, Mass]
  type Decigram  = MetricUnit[W.` -1`.T, Mass]
  type Centigram = MetricUnit[W.` -2`.T, Mass]
  type Milligram = MetricUnit[W.` -3`.T, Mass]
  type Microgram = MetricUnit[W.` -6`.T, Mass]
  type Nanogram  = MetricUnit[W.` -9`.T, Mass]
  type Picogram  = MetricUnit[W.`-12`.T, Mass]
  type Femtogram = MetricUnit[W.`-15`.T, Mass]
  type Attogram  = MetricUnit[W.`-18`.T, Mass]

}

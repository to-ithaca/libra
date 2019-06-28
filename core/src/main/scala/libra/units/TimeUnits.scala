package libra
package units

import shapeless.{ Witness => W }

trait TimeUnits {

  type Time

  type Kilosecond  = MetricUnit[W.`  3`.T, Time]
  type Second      = MetricUnit[W.`  0`.T, Time]
  type Decisecond  = MetricUnit[W.` -1`.T, Time]
  type Centisecond = MetricUnit[W.` -2`.T, Time]
  type Millisecond = MetricUnit[W.` -3`.T, Time]
  type Microsecond = MetricUnit[W.` -6`.T, Time]
  type Nanosecond  = MetricUnit[W.` -9`.T, Time]
  type Picosecond  = MetricUnit[W.`-12`.T, Time]
  type Femtosecond = MetricUnit[W.`-15`.T, Time]
  type Attosecond  = MetricUnit[W.`-18`.T, Time]

  trait Day extends UnitOfMeasure[Time]
  trait Hour extends UnitOfMeasure[Time]

}

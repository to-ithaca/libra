package libra
package units

import shapeless.{ Witness => W }

trait LengthUnits {

  type Length

  type Kilometre  = MetricUnit[W.`  3`.T, Length]
  type Metre      = MetricUnit[W.`  0`.T, Length]
  type Decimetre  = MetricUnit[W.` -1`.T, Length]
  type Centimetre = MetricUnit[W.` -2`.T, Length]
  type Millimetre = MetricUnit[W.` -3`.T, Length]
  type Micrometre = MetricUnit[W.` -6`.T, Length]
  type Nanometre  = MetricUnit[W.` -9`.T, Length]
  type Picometre  = MetricUnit[W.`-12`.T, Length]
  type Femtometre = MetricUnit[W.`-15`.T, Length]
  type Attometre  = MetricUnit[W.`-18`.T, Length]

}

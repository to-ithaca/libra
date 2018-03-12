package libra
package units

trait LengthUnits {

  type Length

  type Kilometre = MetricUnit[3, Length]
  type Metre = MetricUnit[0, Length]
  type Decimetre = MetricUnit[-1, Length]
  type Centimetre = MetricUnit[-2, Length]
  type Millimetre = MetricUnit[-3, Length]
  type Micrometre = MetricUnit[-6, Length]
  type Nanometre = MetricUnit[-9, Length]
  type Picometre = MetricUnit[-12, Length]
  type Femtometre = MetricUnit[-15, Length]
  type Attometre = MetricUnit[-18, Length]

}

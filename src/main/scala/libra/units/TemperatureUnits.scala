package libra
package units

trait TemperatureUnits {

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

}

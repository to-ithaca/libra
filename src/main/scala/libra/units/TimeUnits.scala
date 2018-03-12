package libra
package units


trait TimeUnits {

  type Time

  type Kilosecond = MetricUnit[3, Time]
  type Second = MetricUnit[0, Time]
  type Decisecond = MetricUnit[-1, Time]
  type Centisecond = MetricUnit[-2, Time]
  type Millisecond = MetricUnit[-3, Time]
  type Microsecond = MetricUnit[-6, Time]
  type Nanosecond = MetricUnit[-9, Time]
  type Picosecond = MetricUnit[-12, Time]
  type Femtosecond = MetricUnit[-15, Time]
  type Attosecond = MetricUnit[-18, Time]

  trait Day extends UnitOfMeasure[Time]
  trait Hour extends UnitOfMeasure[Time]

}

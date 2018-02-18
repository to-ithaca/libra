package libra
package dimensions

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait LengthDimension extends MetricUnitImplicits {

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

  implicit def lengthShow: Show[Length] = Show[Length]("L")

  implicit def metricLengthShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Length]] = Show(s"${s()}m")
}

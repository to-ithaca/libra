package libra
package dimensions

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait MassDimension extends MetricUnitImplicits {

  type Mass

  type Kilogram = MetricUnit[3, Time]
  type Gram = MetricUnit[0, Time]
  type Decigram = MetricUnit[-1, Time]
  type Centigram = MetricUnit[-2, Time]
  type Milligram = MetricUnit[-3, Time]
  type Microgram = MetricUnit[-6, Time]
  type Nanogram = MetricUnit[-9, Time]
  type Picogram = MetricUnit[-12, Time]
  type Femtogram = MetricUnit[-15, Time]
  type Attogram = MetricUnit[-18, Time]

  implicit def massShow: Show[Mass] = Show[Mass]("M")

  implicit def metricMassShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Mass]] = Show(s"${s()}g")
}

package libra
package units

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait MassUnits extends MetricUnitImplicits {

  type Mass

  type Kilogram = MetricUnit[3, Mass]
  type Gram = MetricUnit[0, Mass]
  type Decigram = MetricUnit[-1, Mass]
  type Centigram = MetricUnit[-2, Mass]
  type Milligram = MetricUnit[-3, Mass]
  type Microgram = MetricUnit[-6, Mass]
  type Nanogram = MetricUnit[-9, Mass]
  type Picogram = MetricUnit[-12, Mass]
  type Femtogram = MetricUnit[-15, Mass]
  type Attogram = MetricUnit[-18, Mass]

  implicit def massShow: Show[Mass] = Show[Mass]("M")

  implicit def metricMassShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Mass]] = Show(s"${s()}g")
}

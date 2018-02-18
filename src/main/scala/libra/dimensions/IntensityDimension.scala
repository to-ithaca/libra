package libra
package dimensions

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait IntensityDimension {

  type Intensity

  type Candela = UnitOfMeasure[Intensity]

  implicit def intensityShow: Show[Intensity] = Show[Intensity]("J")

  implicit def candelaShow: Show[Candela] = Show[Candela]("cd")
}

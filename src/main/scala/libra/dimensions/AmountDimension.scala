package libra
package dimensions

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait AmountDimension {

  type Amount

  type Mole = UnitOfMeasure[Amount]

  implicit def amountShow: Show[Amount] = Show[Amount]("N")

  implicit def moleShow: Show[Mole] = Show[Mole]("mol")

}

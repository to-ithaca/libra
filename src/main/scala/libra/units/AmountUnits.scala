package libra
package units

import ops.base.Show

trait AmountUnits {

  type Amount

  type Mole = UnitOfMeasure[Amount]

  implicit def amountShow: Show[Amount] = Show[Amount]("N")

  implicit def moleShow: Show[Mole] = Show[Mole]("mol")

}

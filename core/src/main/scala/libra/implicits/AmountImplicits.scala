package libra
package implicits

import ops.base.Show

trait AmountImplicits {

  implicit def amountShow: Show[Amount] = Show[Amount]("N")

  implicit def moleShow: Show[Mole] = Show[Mole]("mol")

}

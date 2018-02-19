package libra
package units

import ops.base.Show

trait IntensityUnits {

  type Intensity

  type Candela = UnitOfMeasure[Intensity]

  implicit def intensityShow: Show[Intensity] = Show[Intensity]("J")

  implicit def candelaShow: Show[Candela] = Show[Candela]("cd")
}

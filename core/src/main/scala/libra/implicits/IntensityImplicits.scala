package libra
package implicits

import ops.base.Show

trait IntensityImplicits {

  implicit def intensityShow: Show[Intensity] = Show[Intensity]("J")

  implicit def candelaShow: Show[Candela] = Show[Candela]("cd")
}

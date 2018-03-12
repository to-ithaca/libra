package libra
package units

import singleton.ops._

trait MetricUnit[I <: XInt, D] extends UnitOfMeasure[D]

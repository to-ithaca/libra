package libra
package dimensions

import singleton.ops._
import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait MetricUnit[I <: XInt, D] extends UnitOfMeasure[D]

// not sure where to put these
// If this is inherited by each trait individually, then we can't have separate imports
// I think that's fine.  There's no reason why people shouldn't import the whole thing
trait MetricUnitImplicits {

  implicit val killoShow: Show[3] = Show("k")
  implicit val zeroShow: Show[0] = Show("")
  implicit val deciShow: Show[-1] = Show("d")
  implicit val centiShow: Show[-2] = Show("c")
  implicit val milliShow: Show[-3] = Show("m")
  implicit val microShow: Show[-6] = Show("μ")
  implicit val nanoShow: Show[-9] = Show("n")
  implicit val picoShow: Show[-12] = Show("p")
  implicit val femtoShow: Show[-15] = Show("f")
  implicit val attoShow: Show[-18] = Show("a")

  implicit def metricConversion[A, D, PF <: XInt, PT <: XInt](
    implicit c: ConvertableTo[A],
    ev: Field[A],
    pf: SafeInt[PF],
    pt: SafeInt[PT]
  ): ConversionFactor[A, D, MetricUnit[PF, D], MetricUnit[PT, D]] =
    new ConversionFactor(c.fromInt(10).pow(pf - pt))
}

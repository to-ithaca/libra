package libra
package implicits

import units.MetricUnit
import ops.base.{Show, ConversionFactor}

import spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._

trait MetricUnitImplicits {

  implicit val killoShow: Show[W.`  3`.T] = Show("k")
  implicit val zeroShow:  Show[W.`  0`.T] = Show("")
  implicit val deciShow:  Show[W.` -1`.T] = Show("d")
  implicit val centiShow: Show[W.` -2`.T] = Show("c")
  implicit val milliShow: Show[W.` -3`.T] = Show("m")
  implicit val microShow: Show[W.` -6`.T] = Show("μ")
  implicit val nanoShow:  Show[W.` -9`.T] = Show("n")
  implicit val picoShow:  Show[W.`-12`.T] = Show("p")
  implicit val femtoShow: Show[W.`-15`.T] = Show("f")
  implicit val attoShow:  Show[W.`-18`.T] = Show("a")

  implicit def metricConversion[A, D, PF <: XInt, PT <: XInt](
    implicit c: ConvertableTo[A],
    ev: Field[A],
    pf: SafeInt[PF],
    pt: SafeInt[PT]
  ): ConversionFactor[A, D, MetricUnit[PF, D], MetricUnit[PT, D]] =
    new ConversionFactor(c.fromInt(10).pow(pf - pt))

}

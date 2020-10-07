package libra
package units

import spire.implicits._
import libra.implicits._
import org.scalatest._

class VelocitySpec extends wordspec.AnyWordSpec with matchers.should.Matchers {

  val ε = 1E-6

  "velocity" should {

    "kmps value" in {
      assert(3.0.kmps.to[Metre].value === (3.0E3) +- (ε * 1E-1))
    }

    "mps value" in {
      assert(3.0.mps.value === (3.0))
    }

    "dmps value" in {
      assert(3.0.dmps.to[Metre].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cmps value" in {
      assert(3.0.cmps.to[Metre].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mmps value" in {
      assert(3.0.mmps.to[Metre].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μmps value" in {
      assert(3.0.μmps.to[Metre].value === (3.0E-6) +- (ε * 1E-6))
    }

    "nmps value" in {
      assert(3.0.nmps.to[Metre].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pmps value" in {
      assert(3.0.pmps.to[Metre].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fmps value" in {
      assert(3.0.fmps.to[Metre].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.kmps.show === ("3 km s^-1 [L T^-1]"))
      assert(3.mps.show === ("3 m s^-1 [L T^-1]"))
      assert(3.dmps.show === ("3 dm s^-1 [L T^-1]"))
      assert(3.cmps.show === ("3 cm s^-1 [L T^-1]"))
      assert(3.mmps.show === ("3 mm s^-1 [L T^-1]"))
      assert(3.μmps.show === ("3 μm s^-1 [L T^-1]"))
      assert(3.nmps.show === ("3 nm s^-1 [L T^-1]"))
      assert(3.pmps.show === ("3 pm s^-1 [L T^-1]"))
      assert(3.fmps.show === ("3 fm s^-1 [L T^-1]"))
    }
  }
}

package libra
package units

import spire.implicits._
import libra.implicits._

import org.scalatest._

class AccelerationSpec extends WordSpec with Matchers {

  val ε = 1E-6

  "acceleration" should {

    "kmps2 value" in {
      assert(3.0.kmps2.to[Metre].value === (3.0E3) +- (ε * 1E-1))
    }

    "mps2 value" in {
      assert(3.0.mps2.value === (3.0))
    }

    "dmps2 value" in {
      assert(3.0.dmps2.to[Metre].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cmps2 value" in {
      assert(3.0.cmps2.to[Metre].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mmps2 value" in {
      assert(3.0.mmps2.to[Metre].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μmps2 value" in {
      assert(3.0.μmps2.to[Metre].value === (3.0E-6) +- (ε * 1E-6))
    }

    "nmps2 value" in {
      assert(3.0.nmps2.to[Metre].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pmps2 value" in {
      assert(3.0.pmps2.to[Metre].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fmps2 value" in {
      assert(3.0.fmps2.to[Metre].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.kmps2.show === ("3 km s^-2 [L T^-2]"))
      assert(3.mps2.show === ("3 m s^-2 [L T^-2]"))
      assert(3.dmps2.show === ("3 dm s^-2 [L T^-2]"))
      assert(3.cmps2.show === ("3 cm s^-2 [L T^-2]"))
      assert(3.mmps2.show === ("3 mm s^-2 [L T^-2]"))
      assert(3.μmps2.show === ("3 μm s^-2 [L T^-2]"))
      assert(3.nmps2.show === ("3 nm s^-2 [L T^-2]"))
      assert(3.pmps2.show === ("3 pm s^-2 [L T^-2]"))
      assert(3.fmps2.show === ("3 fm s^-2 [L T^-2]"))
    }
  }
}

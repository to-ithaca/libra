package libra
package units

import spire.implicits._
import libra.implicits._
import org.scalatest._

class LengthSpec extends wordspec.AnyWordSpec with matchers.should.Matchers {

  val ε = 1E-6

  "length" should {

    "km value" in {
      assert(3.0.km.to[Metre].value === (3.0E3) +- (ε * 1E-1))
    }

    "m value" in {
      assert(3.0.m.value === (3.0))
    }

    "dm value" in {
      assert(3.0.dm.to[Metre].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cm value" in {
      assert(3.0.cm.to[Metre].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mm value" in {
      assert(3.0.mm.to[Metre].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μm value" in {
      assert(3.0.μm.to[Metre].value === (3.0E-6) +- (ε * 1E-6))
    }

    "nm value" in {
      assert(3.0.nm.to[Metre].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pm value" in {
      assert(3.0.pm.to[Metre].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fm value" in {
      assert(3.0.fm.to[Metre].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.km.show === ("3 km [L]"))
      assert(3.m.show === ("3 m [L]"))
      assert(3.dm.show === ("3 dm [L]"))
      assert(3.cm.show === ("3 cm [L]"))
      assert(3.mm.show === ("3 mm [L]"))
      assert(3.μm.show === ("3 μm [L]"))
      assert(3.nm.show === ("3 nm [L]"))
      assert(3.pm.show === ("3 pm [L]"))
      assert(3.fm.show === ("3 fm [L]"))
    }
  }
}

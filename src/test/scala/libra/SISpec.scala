package libra

import spire.implicits._
import libra.si._

import org.scalatest._

class SISpec extends WordSpec {

  "length" should {

    "mm value" in {
      assert(3.0.mm.value === (0.003))
    }

    "cm value" in {
      assert(3.0.cm.value === (0.03))
    }

    "m value" in {
      assert(3.0.m.value === (3.0))
    }

    "km value" in {
      assert(3.0.km.value === (3000.0))
    }

    "show" in {
      assert(3.m.show === ("3 m^1 [L^1]"))
    }
  }

  "mass" should {

    "mg value" in {
      assert(3.0.mg.value === (0.000003))
    }
    "g value" in {
      assert(3.0.g.value === (0.003))
    }

    "kg value" in {
      assert(3.0.kg.value === (3.0))
    }

    "show" in {
      assert(3.kg.show === ("3 kg^1 [M^1]"))
    }
  }

  "time" should {

    "ms value" in {
      assert(3.0.ms.value === (0.003))
    }
    "s value" in {
      assert(3.0.s.value === (3.0))
    }

    "show" in {
      assert(3.s.show === ("3 s^1 [T^1]"))
    }
  }

  "current" should {

    "mA value" in {
      assert(3.0.mA.value === (0.003))
    }
    "A value" in {
      assert(3.0.A.value === (3.0))
    }

    "show" in {
      assert(3.A.show === ("3 A^1 [I^1]"))
    }
  }
}

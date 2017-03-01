package libra

import spire.implicits._
import libra.si._

import org.scalatest._

class SISpec extends WordSpec {

  "length" should {

    "mm baseValue" in {
      assert(3.0.mm.baseValue === (0.003))
    }

    "cm baseValue" in {
      assert(3.0.cm.baseValue === (0.03))
    }

    "m baseValue" in {
      assert(3.0.m.baseValue === (3.0))
    }

    "km baseValue" in {
      assert(3.0.km.baseValue === (3000.0))
    }

    "show" in {
      assert(3.m.show === ("3 * 10^0 m^1 [L^1]"))
    }
  }

  "mass" should {

    "mg baseValue" in {
      assert(3.0.mg.baseValue === (0.000003))
    }
    "g baseValue" in {
      assert(3.0.g.baseValue === (0.003))
    }

    "kg baseValue" in {
      assert(3.0.kg.baseValue === (3.0))
    }

    "show" in {
      assert(3.kg.show === ("3 * 10^0 kg^1 [M^1]"))
    }
  }

  "time" should {

    "ms baseValue" in {
      assert(3.0.ms.baseValue === (0.003))
    }
    "s baseValue" in {
      assert(3.0.s.baseValue === (3.0))
    }

    "show" in {
      assert(3.s.show === ("3 * 10^0 s^1 [T^1]"))
    }
  }

  "current" should {

    "mA baseValue" in {
      assert(3.0.mA.baseValue === (0.003))
    }
    "A baseValue" in {
      assert(3.0.A.baseValue === (3.0))
    }

    "show" in {
      assert(3.A.show === ("3 * 10^0 A^1 [I^1]"))
    }
  }
}

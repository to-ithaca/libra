package libra

import spire.implicits._
import libra.si._

import org.scalatest._

class SISpec extends WordSpec {

  "length" should {

    "mm value" in {
      assert(3.0.mm.to[Metre].value === (0.003))
    }

    "cm value" in {
      assert(3.0.cm.to[Metre].value === (0.03))
    }

    "m value" in {
      assert(3.0.m.value === (3.0))
    }

    "km value" in {
      assert(3.0.km.to[Metre].value === (3000.0))
    }

    "show" in {
      assert(3.mm.show === ("3 mm [L]"))
      assert(3.cm.show === ("3 cm [L]"))
      assert(3.m.show === ("3 m [L]"))
      assert(3.km.show === ("3 km [L]"))
    }
  }

  "mass" should {

    "mg value" in {
      assert(3.0.mg.to[Kilogram].value === (0.000003))
    }
    "g value" in {
      assert(3.0.g.to[Kilogram].value === (0.003))
    }

    "kg value" in {
      assert(3.0.kg.value === (3.0))
    }

    "show" in {
      assert(3.mg.show === ("3 mg [M]"))
      assert(3.g.show === ("3 g [M]"))
      assert(3.kg.show === ("3 kg [M]"))
    }
  }

  "time" should {

    "ms value" in {
      assert(3.0.ms.to[Second].value === (0.003))
    }
    "s value" in {
      assert(3.0.s.value === (3.0))
    }

    "show" in {
      assert(3.ms.show === ("3 ms [T]"))
      assert(3.s.show === ("3 s [T]"))
    }
  }

  "current" should {

    "mA value" in {
      assert(3.0.mA.to[Ampere].value === (0.003))
    }
    "A value" in {
      assert(3.0.A.value === (3.0))
    }

    "show" in {
      assert(3.mA.show === ("3 mA [I]"))
      assert(3.A.show === ("3 A [I]"))
    }
  }
}

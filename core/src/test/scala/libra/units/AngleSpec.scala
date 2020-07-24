package libra
package units

import spire.implicits._
import spire.math._
import libra.implicits._
import org.scalatest._

class AngleSpec extends wordspec.AnyWordSpec with matchers.should.Matchers {

  val ε = 1E-9

  "degree" should {
    "show" in {
      assert(2.degree.show === "2 degree [∠]")
    }

    "arcminute value" in {
      assert(2.0.degree.to[Arcminute].value === 120.0)
    }

    "arcsecond value" in {
      assert(2.0.degree.to[Arcsecond].value === 7200.0)
    }

    "radian value" in {
      assert(180.0.degree.to[Radian].value === pi)
    }

    "gradian value" in {
      assert(180.0.degree.to[Gradian].value === 200.0)
    }

    "turn value" in {
      assert(720.0.degree.to[Turn].value === 2.0)
    }
  }

  "arcminute" should {
    "show" in {
      assert(2.arcminute.show === "2 arcminute [∠]")
    }

    "degree value" in {
      assert(120.0.arcminute.to[Degree].value === 2.0)
    }

    "arcsecond value" in {
      assert(2.0.arcminute.to[Arcsecond].value === 120.0)
    }

    "radian value" in {
      assert(10800.0.arcminute.to[Radian].value === pi)
    }

    "gradian value" in {
      assert(10800.0.arcminute.to[Gradian].value === 200.0)
    }

    "turn value" in {
      assert(21600.0.arcminute.to[Turn].value === 1.0)
    }
  }

  "arcsecond" should {
    "show" in {
      assert(2.arcsecond.show === "2 arcsecond [∠]")
    }

    "degree value" in {
      assert(7200.0.arcsecond.to[Degree].value === 2.0)
    }

    "arcminute value" in {
      assert(120.0.arcsecond.to[Arcminute].value === 2.0)
    }

    "radian value" in {
      assert(648000.0.arcsecond.to[Radian].value === pi)
    }

    "gradian value" in {
      assert(648000.0.arcsecond.to[Gradian].value === 200.0 +- ε)
    }

    "turn value" in {
      assert(1296000.0.arcsecond.to[Turn].value === (1.0) +- ε)
    }
  }
}

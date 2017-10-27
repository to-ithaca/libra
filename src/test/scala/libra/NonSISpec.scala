package libra

import spire.implicits._
import libra.nonsi._, libra.si._

import org.scalatest._


class NonSISpec extends WordSpec {

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
  }
}

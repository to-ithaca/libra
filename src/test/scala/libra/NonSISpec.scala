package libra

import spire.implicits._
import spire.math._
import libra.nonsi._
import libra.si._

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

    "radian value" in {
      assert(180.0.degree.to[Radian].value === pi)
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

  "degreesPerSecond" should {
    "show" in {
      assert(2.degreessPerSecond.show === "2 degree s^-1 [∠ T^-1]")
    }

    "arcminutesPerSecond value" in {
      assert(2.0.degreessPerSecond.to[Arcminute].value === 120.0)
    }

    "arcsecondsPerSecond value" in {
      assert(2.0.degreessPerSecond.to[Arcsecond].value === 7200.0)
    }
  }

  "arcminutesPerSecond" should {
    "show" in {
      assert(2.arcminutesPerSecond.show === "2 arcminute s^-1 [∠ T^-1]")
    }

    "degreesPerSecond value" in {
      assert(120.0.arcminutesPerSecond.to[Degree].value === 2.0)
    }

    "arcsecond value" in {
      assert(2.0.arcminutesPerSecond.to[Arcsecond].value === 120.0)
    }

    "radian value" in {
      assert(10800.0.arcminutesPerSecond.to[Radian].value === pi)
    }
  }

  "arcsecondsPerSecond" should {
    "show" in {
      assert(2.arcsecondsPerSecond.show === "2 arcsecond s^-1 [∠ T^-1]")
    }

    "degreesPerSecond value" in {
      assert(7200.0.arcsecondsPerSecond.to[Degree].value === 2.0)
    }

    "arcminutesPerSecond value" in {
      assert(120.0.arcsecondsPerSecond.to[Arcminute].value === 2.0)
    }

    "radian value" in {
      assert(648000.0.arcsecondsPerSecond.to[Radian].value === pi)
    }
  }

  "radians" should {
    "show" in {
      assert(2.radian.show === "2 rad [∠]")
    }

    "degree value" in {
      assert(pi.radian.to[Degree].value === 180.0)
    }

    "arcminute value" in {
      assert(pi.radian.to[Arcminute].value === 10800.0)
    }

    "arcsecond value" in {
      assert(pi.radian.to[Arcsecond].value === 648000.0)
    }
  }
}

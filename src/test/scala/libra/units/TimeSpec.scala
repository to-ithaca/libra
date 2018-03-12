package libra
package units

import spire.implicits._
import libra.implicits._
import org.scalatest._

class TimeSpec extends WordSpec with Matchers {

  val ε = 1E-6

  "time" should {

    "ks value" in {
      assert(3.0.ks.to[Second].value === (3.0E3) +- (ε * 1E-1))
    }

    "s value" in {
      assert(3.0.s.value === (3.0))
    }

    "ds value" in {
      assert(3.0.ds.to[Second].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cs value" in {
      assert(3.0.cs.to[Second].value === (3.0E-2) +- (ε * 1E-2))
    }

    "ms value" in {
      assert(3.0.ms.to[Second].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μs value" in {
      assert(3.0.μs.to[Second].value === (3.0E-6) +- (ε * 1E-6))
    }

    "ns value" in {
      assert(3.0.ns.to[Second].value === (3.0E-9) +- (ε * 1E-9))
    }

    "ps value" in {
      assert(3.0.ps.to[Second].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fs value" in {
      assert(3.0.fs.to[Second].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.ks.show === ("3 ks [T]"))
      assert(3.s.show === ("3 s [T]"))
      assert(3.ds.show === ("3 ds [T]"))
      assert(3.cs.show === ("3 cs [T]"))
      assert(3.ms.show === ("3 ms [T]"))
      assert(3.μs.show === ("3 μs [T]"))
      assert(3.ns.show === ("3 ns [T]"))
      assert(3.ps.show === ("3 ps [T]"))
      assert(3.fs.show === ("3 fs [T]"))
    }
  }

  "day" should {
    "show" in {
      assert(2.days.show === ("2 days [T]"))
    }

    "hour value" in {
      assert(2.0.days.to[Hour].value === (48.0))
    }
  }

  "hour" should {
    "show" in {
      assert(2.hours.show === ("2 hours [T]"))
    }

    "day value" in {
      assert(24.0.hours.to[Day].value === (1.0))
    }
  }
}

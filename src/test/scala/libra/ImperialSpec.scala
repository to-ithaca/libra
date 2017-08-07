package libra

import spire.implicits._
import libra.imperial._, libra.si._

import org.scalatest._

class ImperialSpec extends WordSpec {

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

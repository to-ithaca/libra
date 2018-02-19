package libra
package units

import spire.implicits._
import libra.implicits._
import org.scalatest._

class MomentumSpec extends WordSpec with Matchers {

  "momentum" should {

    "kgmps value" in {
      assert(3.0.kgmps.to[Centimetre].value === (300.0))
    }

    "show" in {
      assert(3.kgmps.show === ("3 kg m s^-1 [M L T^-1]"))
    }
  }
}

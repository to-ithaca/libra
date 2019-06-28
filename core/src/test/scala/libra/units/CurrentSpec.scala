package libra
package units

import spire.implicits._
import libra.implicits._
import org.scalatest._

class CurrentSpec extends WordSpec with Matchers {

  val ε = 1E-6

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

  "momentum" should {

    "kgmps value" in {
      assert(3.0.kgmps.to[Centimetre].value === (300.0))
    }

    "show" in {
      assert(3.kgmps.show === ("3 kg m s^-1 [M L T^-1]"))
    }
  }
}

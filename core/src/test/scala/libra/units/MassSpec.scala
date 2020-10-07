package libra
package units

import spire.implicits._
import libra.implicits._
import org.scalatest._

class MassSpec extends wordspec.AnyWordSpec with matchers.should.Matchers {

  val ε = 1E-6

  "mass" should {

    "kg value" in {
      assert(3.0.kg.to[Gram].value === (3.0E3) +- (ε * 1E-1))
    }

    "g value" in {
      assert(3.0.g.value === (3.0))
    }

    "dg value" in {
      assert(3.0.dg.to[Gram].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cg value" in {
      assert(3.0.cg.to[Gram].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mg value" in {
      assert(3.0.mg.to[Gram].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μg value" in {
      assert(3.0.μg.to[Gram].value === (3.0E-6) +- (ε * 1E-6))
    }

    "ng value" in {
      assert(3.0.ng.to[Gram].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pg value" in {
      assert(3.0.pg.to[Gram].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fg value" in {
      assert(3.0.fg.to[Gram].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.kg.show === ("3 kg [M]"))
      assert(3.g.show === ("3 g [M]"))
      assert(3.dg.show === ("3 dg [M]"))
      assert(3.cg.show === ("3 cg [M]"))
      assert(3.mg.show === ("3 mg [M]"))
      assert(3.μg.show === ("3 μg [M]"))
      assert(3.ng.show === ("3 ng [M]"))
      assert(3.pg.show === ("3 pg [M]"))
      assert(3.fg.show === ("3 fg [M]"))
    }
  }
}

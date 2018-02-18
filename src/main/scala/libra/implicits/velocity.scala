package libra
package implicits

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait VelocityKilometresPerSecondImplicits {
  implicit final class VelocityKilometresPerSecondOps[A](val a: A) {
     def km: VelocityQuantity[A, Velocity, KilometresPerSecond] = Quantity(a)
  }
}

trait VelocityMetresPerSecondImplicits {
  implicit final class VelocityMetresPerSecondOps[A](val a: A) {
     def m: VelocityQuantity[A, Velocity, MetresPerSecond] = Quantity(a)
  }
}

trait VelocityDecimetresPerSecondImplicits {
  implicit final class VelocityDecimetresPerSecondOps[A](val a: A) {
     def dm: VelocityQuantity[A, Velocity, DecimetresPerSecond] = Quantity(a)
  }
}

trait VelocityCentimetresPerSecondImplicits {
  implicit final class VelocityCentimetresPerSecondOps[A](val a: A) {
     def cm: VelocityQuantity[A, Velocity, CentimetresPerSecond] = Quantity(a)
  }
}

trait VelocityMillimetresPerSecondImplicits {
  implicit final class VelocityMillimetresPerSecondOps[A](val a: A) {
     def mm: VelocityQuantity[A, Velocity, MillimetresPerSecond] = Quantity(a)
  }
}

trait VelocityMicrometresPerSecondImplicits {
  implicit final class VelocityMicrometresPerSecondOps[A](val a: A) {
    def μm: VelocityQuantity[A, Velocity, MicrometresPerSecond] = Quantity(a)
  }
}

trait VelocityNanometresPerSecondImplicits {
  implicit final class VelocityNanometresPerSecondOps[A](val a: A) {
     def nm: VelocityQuantity[A, Velocity, NanometresPerSecond] = Quantity(a)
  }
}

trait VelocityPicometresPerSecondImplicits {
  implicit final class VelocityPicometresPerSecondOps[A](val a: A) {
     def pm: VelocityQuantity[A, Velocity, PicometresPerSecond] = Quantity(a)
  }
}

trait VelocityFemtometresPerSecondImplicits {
  implicit final class VelocityFemtometresPerSecondOps[A](val a: A) {
     def fm: VelocityQuantity[A, Velocity, FemtometresPerSecond] = Quantity(a)
  }
}

trait VelocityAttometresPerSecondImplicits {
  implicit final class VelocityAttometresPerSecondOps[A](val a: A) {
     def fm: VelocityQuantity[A, Velocity, AttometresPerSecond] = Quantity(a)
  }
}

trait VelocityImplicits extends VelocityKilometresPerSecondImplicits
    with VelocityMetresPerSecondImplicits
    with VelocityDecimetresPerSecondImplicits
    with VelocityCentimetresPerSecondImplicits
    with VelocityMillimetresPerSecondImplicits
    with VelocityMicrometresPerSecondImplicits
    with VelocityNanometresPerSecondImplicits
    with VelocityPicometresPerSecondImplicits
    with VelocityFemtometresPerSecondImplicits

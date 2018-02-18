package libra
package implicits

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

trait MassKilogramImplicits {
  implicit final class MassKilogramOps[A](val a: A) {
     def kg: QuantityOf[A, Mass, Kilogram] = Quantity(a)
  }
}

trait MassGramImplicits {
  implicit final class MassGramOps[A](val a: A) {
     def g: QuantityOf[A, Mass, Gram] = Quantity(a)
  }
}

trait MassDecigramImplicits {
  implicit final class MassDecigramOps[A](val a: A) {
     def dg: QuantityOf[A, Mass, Decigram] = Quantity(a)
  }
}

trait MassCentigramImplicits {
  implicit final class MassCentigramOps[A](val a: A) {
     def cg: QuantityOf[A, Mass, Centigram] = Quantity(a)
  }
}

trait MassMilligramImplicits {
  implicit final class MassMilligramOps[A](val a: A) {
     def mg: QuantityOf[A, Mass, Milligram] = Quantity(a)
  }
}

trait MassMicrogramImplicits {
  implicit final class MassMicrogramOps[A](val a: A) {
    def μg: QuantityOf[A, Mass, Microgram] = Quantity(a)
  }
}

trait MassNanogramImplicits {
  implicit final class MassNanogramOps[A](val a: A) {
     def ng: QuantityOf[A, Mass, Nanogram] = Quantity(a)
  }
}

trait MassPicogramImplicits {
  implicit final class MassPicogramOps[A](val a: A) {
     def pg: QuantityOf[A, Mass, Picogram] = Quantity(a)
  }
}

trait MassFemtogramImplicits {
  implicit final class MassFemtogramOps[A](val a: A) {
     def fg: QuantityOf[A, Mass, Femtogram] = Quantity(a)
  }
}

trait MassAttogramImplicits {
  implicit final class MassAttogramOps[A](val a: A) {
     def fg: QuantityOf[A, Mass, Attogram] = Quantity(a)
  }
}

trait MassImplicits extends MassKilogramImplicits
    with MassGramImplicits
    with MassDecigramImplicits
    with MassCentigramImplicits
    with MassMilligramImplicits
    with MassMicrogramImplicits
    with MassNanogramImplicits
    with MassPicogramImplicits
    with MassFemtogramImplicits

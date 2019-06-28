package libra

import org.scalatest._
import shapeless.{ Witness => W, _ }
import spire.implicits._
import libra.ops.quantity._, libra.implicits._

class QuantitySpec extends FlatSpec {

  it should "align as" in {
    type D0 = Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: HNil
    type D1 = Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] ::  HNil
    type Q0 = Quantity[Int, D0]
    type Q1 = Quantity[Int, D1]

    val q0: Q0 = Quantity[Int, D0](2)
    val q1: Q1 = q0.as[D1]
  }

  it should "add" in {
    type D0 = Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: HNil
    type D1 = Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] ::  HNil
    type Q0 = Quantity[Int, D0]
    type Q1 = Quantity[Int, D1]
    the[Add[Q0, Q1]]

    val q0 = Quantity[Int, D0](2)
    val q1 = Quantity[Int, D1](3)
    assert((q0 + q1) === Quantity[Int, D0](5))
    assert((q1 + q0) === Quantity[Int, D1](5))
  }

  it should "multiply" in {
    type D0 = Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: HNil
    type D1 = Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] ::  HNil
    type Q0 = Quantity[Int, D0]
    type Q1 = Quantity[Int, D1]
    the[Multiply[Q0, Q1]]

    val q0 = Quantity[Int, D0](2)
    val q1 = Quantity[Int, D1](3)
    assert((q0 * q1) === Quantity[Int, Term[Length, Metre, Fraction[W.`2`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`2`.T, W.`1`.T]] :: HNil](6))
  }

  it should "invert" in {
    type Q = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    type Inverse = Quantity[Double, Term[Length, Metre, Fraction[W.`-1`.T, W.`1`.T]] :: HNil]
    the[Invert.Aux[Q, Inverse]]

    val q = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](2.0)
    assert(q.invert === Quantity[Double, Term[Length, Metre, Fraction[W.`-1`.T, W.`1`.T]] :: HNil](0.5))
  }

  it should "divide" in {

    type Q0 = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    type Q1 = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    type Result = Quantity[Double, HNil]
    the[Divide.Aux[Q0, Q1, Result]]

    val q0 = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](1.0)
    val q1 = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](2.0)
    assert((q0 / q1) === Quantity[Double, HNil](0.5))
  }

  it should "quotient" in {

    type Q0 = Quantity[Int, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    type Q1 = Quantity[Int, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    type Result = Quantity[Int, HNil]
    the[EuclideanDivide.Aux[Q0, Q1, Result]]

    val q0 = Quantity[Int, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](5)
    val q1 = Quantity[Int, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](2)
    assert((q0 /~ q1) === Quantity[Int, HNil](2))
  }

  it should "power" in {
    type Q = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    type QRaised = Quantity[Double, Term[Length, Metre, Fraction[W.`2`.T, W.`1`.T]] :: HNil]
    the[Power.Aux[Q, W.`2`.T, QRaised]]

    val q = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](4.0)
    assert(q.power[W.`2`.T] === Quantity[Double, Term[Length, Metre, Fraction[W.`2`.T, W.`1`.T]] :: HNil](16.0))
  }

  it should "support scalar multiplication" in {
    val q: QuantityOf[Int, Length, Metre] = Quantity(4)
    assert((q :* 3) == Quantity[Int, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](12))
    assert((3 *: q) == Quantity[Int, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: HNil](12))
  }

  it should "show" in {
    type Q = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    the[Show[Q]]

    val q = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: HNil](2.0)
    assert(q.show == "2.0 m kg [L M]")
  }

  it should "convert" in {
    type Q = Quantity[Double, Term[Length, Metre, Fraction[W.`1`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: HNil]
    val q: Q = Quantity(2.0)

    the[ConvertTo[Q, Centimetre]]
    the[ConvertTo[Q, Gram]]

    assert(q.to[Centimetre].to[Gram] == Quantity[Double, Term[Length, Centimetre, Fraction[W.`1`.T, W.`1`.T]] :: Term[Mass, Kilogram, Fraction[W.`1`.T, W.`1`.T]] :: HNil](200000.0))
  }

}

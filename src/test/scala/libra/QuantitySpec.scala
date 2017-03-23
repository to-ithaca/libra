package libra

import org.scalatest._

import shapeless._

import singleton.ops.{Length => _, _}
import singleton.ops.impl._

import spire.implicits._

import libra.ops.quantity._, libra.si._

class QuantitySpec extends FlatSpec {

  it should "add" in {
    type D0 = OneOf[Length] :: OneOf[Mass] :: HNil
    type D1 = OneOf[Mass] :: OneOf[Length] :: HNil
    type Q0 = Quantity[Double, D0]
    type Q1 = Quantity[Double, D1]
    the[Add[Q0, Q1]]

    val q0 = Quantity[Double, D0](2.0)
    val q1 = Quantity[Double, D1](3.0)
    assert((q0 + q1) === Quantity[Double, D0](5.0))
    assert((q1 + q0) === Quantity[Double, D1](5.0))
  }

  it should "multiply" in {
    type D0 = Term[Length, Fraction[1, 1]] :: Term[Mass, Fraction[1, 1]] :: HNil
    type D1 = Term[Mass, Fraction[1, 1]] :: Term[Length, Fraction[1, 1]] :: HNil
    type Q0 = Quantity[Double, D0]
    type Q1 = Quantity[Double, D1]
    the[libra.ops.dimensions.Multiply[D0, D1]]
    the[Multiply[Q0, Q1]]

    val q0 = Quantity[Double, D0](2.0)
    val q1 = Quantity[Double, D1](3.0)
    assert((q0 * q1) === Quantity[Double, Term[Length, Fraction[2, 1]] :: Term[Mass, Fraction[2, 1]] :: HNil](6.0))
  }

  it should "invert" in {
    type Q = Quantity[Double, OneOf[Length] :: HNil]
    type Inverse = Quantity[Double, Term[Length, Fraction[-1, 1]] :: HNil]
    the[Invert.Aux[Q, Inverse]]

    val q = Quantity[Double, OneOf[Length] :: HNil](2.0)
    assert(q.invert === Quantity[Double, Term[Length, Fraction[-1, 1]] :: HNil](0.5))
  }

  it should "divide" in {

    type Q0 = Quantity[Double, OneOf[Length] :: HNil]
    type Q1 = Quantity[Double, OneOf[Length] :: HNil]
    type Result = Quantity[Double, HNil]
    the[Divide.Aux[Q0, Q1, Result]]

    val q0 = Quantity[Double, OneOf[Length] :: HNil](1.0)
    val q1 = Quantity[Double, OneOf[Length] :: HNil](2.0)
    assert((q0 / q1) === Quantity[Double, HNil](0.5))
  }

  it should "power" in {
    type Q = Quantity[Double, OneOf[Length] :: HNil]
    type QRaised = Quantity[Double, Term[Length, Fraction[2, 1]] :: HNil]
    the[Power.Aux[Q, 2, QRaised]]

    val q = Quantity[Double, OneOf[Length] :: HNil](4.0)
    assert(q.power[2] === Quantity[Double, Term[Length, Fraction[2, 1]] :: HNil](16.0))
  }

  it should "show" in {
    type Q = Quantity[Double, OneOf[Length] :: OneOf[Mass] :: HNil]
    the[Show[Q]]

    val q = Quantity[Double, OneOf[Length] :: OneOf[Mass] :: HNil](2.0)
    println(q.show)
    assert(q.show == "2.0 m kg [L M]")
  }
}

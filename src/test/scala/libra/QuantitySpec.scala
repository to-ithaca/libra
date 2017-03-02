package libra

import org.scalatest._

import shapeless._
import libra.ops.quantity._
import baseDimensions._

import singleton.ops.{Length => _, _}
import singleton.ops.impl._

import spire.implicits._

class QuantitySpec extends FlatSpec {
  it should "prefix" in {
    type D = Dimensions[Length :: Mass :: HNil, HNil]
    the[Prefix[Quantity[Double, 4, D], 5]]

    val q = Quantity[Double, 4, D](2.0)
    assert(q.prefix[5] === Quantity[Double, 5, D](0.2))
  }

  it should "add" in {
    type D0 = Dimensions[Length :: Mass :: HNil, HNil]
    type D1 = Dimensions[Mass :: Length :: HNil, HNil]
    type Q0 = Quantity[Double, 3, Dimensions[Length :: Mass :: HNil, HNil]]
    type Q1 = Quantity[Double, 4, Dimensions[Mass :: Length :: HNil, HNil]]
    the[Add[Q0, Q1]]

    val q0 = Quantity[Double, 3, D0](2.0)
    val q1 = Quantity[Double, 4, D1](3.0)
    assert(q0.add(q1) === Quantity[Double, 4, D0](3.2))
    assert(q1.add(q0) === Quantity[Double, 4, D1](3.2))
  }

  it should "multiply" in {
    type D0 = Dimensions[Length :: Mass :: HNil, HNil]
    type D1 = Dimensions[Mass :: Length :: HNil, HNil]
    type Q0 = Quantity[Double, 3, Dimensions[Length :: Mass :: HNil, HNil]]
    type Q1 = Quantity[Double, 4, Dimensions[Mass :: Length :: HNil, HNil]]
    the[Multiply[Q0, Q1]]

    val q0 = Quantity[Double, 3, D0](2.0)
    val q1 = Quantity[Double, 4, D1](3.0)
    assert(q0.multiply(q1) === Quantity[Double, 7, Dimensions[Length :: Mass :: Mass :: Length :: HNil, HNil]](6.0))
  }

  it should "invert" in {
    type Q = Quantity[Double, 3, Dimensions[Length :: HNil, HNil]]
    type Inverse = Quantity[Double, -3, Dimensions[HNil, Length :: HNil]]
    the[Invert.Aux[Q, Inverse]]

    val q = Quantity[Double, 3, Dimensions[Length :: HNil, HNil]](2.0)
    assert(q.invert === Quantity[Double, -3, Dimensions[HNil, Length :: HNil]](0.5))
  }

  it should "divide" in {
    type Q0 = Quantity[Double, 3, Dimensions[Length :: HNil, HNil]]
    type Q1 = Quantity[Double, 2, Dimensions[Length :: HNil, HNil]]
    type Result = Quantity[Double, 1, Dimensions[HNil, HNil]]
    the[Divide.Aux[Q0, Q1, Result]]

    val q0 = Quantity[Double, 3, Dimensions[Length :: HNil, HNil]](1.0)
    val q1 = Quantity[Double, 2, Dimensions[Length :: HNil, HNil]](2.0)
    assert(q0.divide(q1) === Quantity[Double, 1, Dimensions[HNil, HNil]](0.5))
  }

  it should "power" in {
    type Q = Quantity[Double, 3, Dimensions[Length :: HNil, HNil]]
    type QRaised = Quantity[Double, 6, Dimensions[Length :: Length ::HNil, HNil]]
    the[Power.Aux[Q, 2, QRaised]]

    val q = Quantity[Double, 3, Dimensions[Length :: HNil, HNil]](4.0)
    assert(q.power[2] === Quantity[Double, 6, Dimensions[Length :: Length :: HNil, HNil]](16.0))
  }

  it should "show" in {
    type Q = Quantity[Double, 3, Dimensions[Length :: Mass :: HNil, HNil]]
    the[Show[Q]]

    val q = Quantity[Double, 3, Dimensions[Length :: Mass :: HNil, HNil]](2.0)
    assert(q.show == "2.0 * 10^3 m^1 kg^1 [L^1 M^1]")
  }
}

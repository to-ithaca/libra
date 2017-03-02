package libra

import org.scalatest._

import shapeless._
import libra.ops.quantity._
import baseDimensions._

import singleton.ops.{Length => _, _}
import singleton.ops.impl._

import spire.implicits._

class BaseQuantitySpec extends FlatSpec {
  it should "use m for metres" in {
    assert((3.m add 2.m) === (5.m))
  }

  it should "use kg for kilograms" in {
    assert((3.kg add 2.kg) === (5.kg))
  }

  it should "use s for seconds" in {
    assert((3.s add 2.s) === (5.s))
  }
}

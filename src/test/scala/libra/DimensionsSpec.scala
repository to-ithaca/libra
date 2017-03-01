package libra

import org.scalatest._

import shapeless._
import shapeless.test._
import libra.ops.dimensions._, libra.si._

class DimensionsSpec extends FlatSpec {
  it should "eq same dimensions" in {
    type LM_SS = Dimensions[Length :: Mass :: HNil, Time :: Time :: HNil]
    type ML_SS = Dimensions[Mass :: Length :: HNil, Time :: Time :: HNil]
    the[Eq[LM_SS, ML_SS]]
  }

  it should "not eq different dimensions" in {
    type LM_SS = Dimensions[Length :: Mass :: HNil, Time :: Time :: HNil]
    type ML_S = Dimensions[Mass :: Length :: HNil, Time :: HNil]
    illTyped("""the[Eq[LM_SS, ML_S]]""")
  }

  it should "simplify" in {
    type Unsimplified = Dimensions[Length :: Mass :: Length :: HNil, Length :: Length :: HNil]
    type Simplified = Dimensions[Mass :: HNil, HNil]
    the[Simplify.Aux[Unsimplified, Simplified]]
  }

  it should "multiply" in {
    type LM_SS = Dimensions[Length :: Mass :: HNil, Time :: Time :: HNil]
    type SS_L = Dimensions[Time :: Time :: HNil, Length :: HNil]
    type M = Dimensions[Mass :: HNil, HNil]
    the[Multiply.Aux[LM_SS, SS_L, M]]
  }

  it should "invert" in {
    type LM_S = Dimensions[Length :: Mass :: HNil, Time :: HNil]
    type S_LM = Dimensions[Time :: HNil, Length :: Mass :: HNil]
    the[Invert.Aux[LM_S, S_LM]]
  }

  it should "divide" in {
    type LM_S = Dimensions[Length :: Mass :: HNil, Time :: HNil]
    type EmptyDim = Dimensions[HNil, HNil]
    the[Divide.Aux[LM_S, LM_S, EmptyDim]]
  }

  it should "raise to a positive power" in {
    type LM_S = Dimensions[Length :: Mass :: HNil, Time :: HNil]
    type LLMM_SS = Dimensions[Length :: Length :: Mass :: Mass :: HNil, Time :: Time :: HNil]
    val power = the[Power[LM_S, 2]]
    the[Eq[power.Out, LLMM_SS]]
  }

  it should "raise to a negative power" in {
    type LM_S = Dimensions[Length :: Mass :: HNil, Time :: HNil]
    type SS_LLMM = Dimensions[Time :: Time :: HNil, Length :: Length :: Mass :: Mass :: HNil]
    val power = the[Power[LM_S, -2]]
    the[Eq[power.Out, SS_LLMM]]
  }

  it should "raise to the power 0" in {
    type LM_S = Dimensions[Length :: Mass :: HNil, Time :: HNil]
    the[Power.Aux[LM_S, 0, Dimensions[HNil, HNil]]]
  }

  it should "show dimensions" in {
    type LM_S = Dimensions[Length :: Mass :: HNil, Time :: HNil]
    val s = the[ShowDimension[LM_S]]
    assert(s() == "L^1 M^1  T^-1")
  }

  it should "show units" in {
    type LM_S = Dimensions[Length :: Mass :: HNil, Time :: HNil]
    val s = the[ShowUnit[LM_S]]
    assert(s() == "m^1 kg^1  s^-1")
  }
}

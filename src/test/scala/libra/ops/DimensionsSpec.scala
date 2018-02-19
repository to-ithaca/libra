package libra
package ops

import org.scalatest._
import shapeless._
import shapeless.test._
import libra.ops.dimensions._, libra.implicits._
import spire.implicits._

class DimensionsSpec extends FlatSpec {

  it should "multiply" in {

    type S0 = Term[Time, Second, Fraction[-2, 1]] :: HNil
    type S1 = Term[Time, Second, Fraction[4, 1]] :: HNil
    type L_SS = Term[Length, Metre, Fraction[1, 1]] :: Term[Time, Second, Fraction[-2, 1]] :: HNil
    type SS_L = Term[Length, Metre, Fraction[-1, 1]] :: Term[Time, Second, Fraction[2, 1]] :: HNil
    type SS_ML = Term[Length, Metre, Fraction[-1, 1]] :: Term[Time, Second, Fraction[2, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: HNil

    the[Multiply[HNil, SS_L]]
    the[Multiply[L_SS, SS_L]]


    the[Multiply.Aux[SS_L, L_SS, HNil]]
    the[Multiply.Aux[L_SS, SS_ML, Term[Mass, Kilogram, Fraction[1, 1]] :: HNil]]
    the[Multiply.Aux[SS_ML, L_SS, Term[Mass, Kilogram, Fraction[1, 1]] :: HNil]]

    type D0 = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: HNil
    type D1 = Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Length, Metre, Fraction[1, 1]] :: HNil

    the[Multiply[D0, D1]]
  }

  it should "invert" in {
    type LM_S = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Time, Second, Fraction[-1, 1]] :: HNil
    type S_LM = Term[Length, Metre, Fraction[-1, 1]] :: Term[Mass, Kilogram, Fraction[-1, 1]] :: Term[Time, Second, Fraction[1, 1]] :: HNil
    the[Invert.Aux[LM_S, S_LM]]
  }

  it should "divide" in {
    type LM_S = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Time, Second, Fraction[-1, 1]] :: HNil
    the[Divide.Aux[LM_S, LM_S, HNil]]
  }

  it should "raise to a positive power" in {
    type LM_S = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Time, Second, Fraction[-1, 1]] :: HNil
    type LLMM_SS = Term[Length, Metre, Fraction[2, 1]] :: Term[Mass, Kilogram, Fraction[2, 1]] :: Term[Time, Second, Fraction[-2, 1]] :: HNil
    the[Power.Aux[2, LM_S, LLMM_SS]]
  }

  it should "raise to a negative power" in {
    type LM_S = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Time, Second, Fraction[-1, 1]] :: HNil
    type SS_LLMM = Term[Length, Metre, Fraction[-2, 1]] :: Term[Mass, Kilogram, Fraction[-2, 1]] :: Term[Time, Second, Fraction[2, 1]] :: HNil
    the[Power.Aux[-2, LM_S, SS_LLMM]]
  }

  it should "raise to the power 0" in {
    type LM_S = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Time, Second, Fraction[-1, 1]] :: HNil
    the[Power.Aux[0, LM_S, HNil]]
  }

  it should "show dimensions" in {
    type LM_S = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Time, Second, Fraction[-1, 1]] :: HNil
    val s = the[ShowDimension[LM_S]]
    assert(s() == "L M T^-1 ")
  }

  it should "show units" in {
    type LM_S = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Time, Second, Fraction[-1, 1]] :: HNil
    val s = the[ShowUnit[LM_S]]
    assert(s() == "m kg s^-1 ")
  }

  it should "convert" in {
    type L = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: HNil
    type LGram = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Gram, Fraction[1, 1]] :: HNil
    the[libra.ops.base.Conversion[Double, Length, Metre, Centimetre]]
    the[ConvertTo[Double, Centimetre, L]]

    the[libra.ops.base.Conversion[Double, Mass, Kilogram, Gram]]
    the[ConvertTo.Aux[Double, Gram, L, LGram]]
  }
}

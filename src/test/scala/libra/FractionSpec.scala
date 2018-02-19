package libra

import org.scalatest._
import shapeless._
import singleton.ops._
import libra.ops.fraction._

class FractionSpec extends FlatSpec {

  it should "find the greatest common divisor" in {
    the[GCD.Aux[6, 6, 6]]
    the[GCD.Aux[6, 3, 3]]
    the[GCD.Aux[12, 8, 4]]
    the[GCD.Aux[7, 5, 1]]
    the[GCD.Aux[5, 7, 1]]
  }

  it should "negate" in {
    the[Negate.Aux[Fraction[1, 1], Fraction[-1, 1]]]
  }

  it should "simplify" in {
    the[Simplify.Aux[Fraction[12, 8], Fraction[3, 2]]]
    the[Simplify.Aux[Fraction[8, 12], Fraction[2, 3]]]
    the[Simplify.Aux[Fraction[-8, 12], Fraction[-2, 3]]]
    the[Simplify.Aux[Fraction[1, 1], Fraction[1, 1]]]
  }

  it should "add" in {
    the[Add.Aux[Fraction[1, 3], Fraction[2, 3], Fraction[1, 1]]]
    the[Add.Aux[Fraction[1, 3], Fraction[-2, 3], Fraction[-1, 3]]]
    the[Add.Aux[Fraction[1, 1], Fraction[-1, 1], Fraction[0, 1]]]
    the[Add.Aux[Fraction[1, 1], Fraction[1, 1], Fraction[2, 1]]]
  }

  it should "subtract" in {
    the[Subtract.Aux[Fraction[2, 3], Fraction[-1, 3], Fraction[1, 1]]]
  }

  it should "multiply" in {
    the[Multiply.Aux[Fraction[2, 3], Fraction[4, 3], Fraction[8, 9]]]
  }
}

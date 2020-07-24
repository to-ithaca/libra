package libra

import org.scalatest._
import shapeless.{ Witness => W, _ }
import singleton.ops._
import libra.ops.fraction._

class FractionSpec extends flatspec.AnyFlatSpec {

  it should "find the greatest common divisor" in {
    the[GCD.Aux[W.` 6`.T, W.`6`.T, W.`6`.T]]
    the[GCD.Aux[W.` 6`.T, W.`3`.T, W.`3`.T]]
    the[GCD.Aux[W.`12`.T, W.`8`.T, W.`4`.T]]
    the[GCD.Aux[W.` 7`.T, W.`5`.T, W.`1`.T]]
    the[GCD.Aux[W.` 5`.T, W.`7`.T, W.`1`.T]]
  }

  it should "negate" in {
    the[Negate.Aux[Fraction[W.`1`.T, W.`1`.T], Fraction[W.`-1`.T, W.`1`.T]]]
  }

  it should "simplify" in {
    the[Simplify.Aux[Fraction[W.`12`.T, W.` 8`.T], Fraction[W.` 3`.T, W.`2`.T]]]
    the[Simplify.Aux[Fraction[W.` 8`.T, W.`12`.T], Fraction[W.` 2`.T, W.`3`.T]]]
    the[Simplify.Aux[Fraction[W.`-8`.T, W.`12`.T], Fraction[W.`-2`.T, W.`3`.T]]]
    the[Simplify.Aux[Fraction[W.` 1`.T, W.` 1`.T], Fraction[W.` 1`.T, W.`1`.T]]]
  }

  it should "add" in {
    the[Add.Aux[Fraction[W.`1`.T, W.`3`.T], Fraction[W.` 2`.T, W.`3`.T], Fraction[W.` 1`.T, W.`1`.T]]]
    the[Add.Aux[Fraction[W.`1`.T, W.`3`.T], Fraction[W.`-2`.T, W.`3`.T], Fraction[W.`-1`.T, W.`3`.T]]]
    the[Add.Aux[Fraction[W.`1`.T, W.`1`.T], Fraction[W.`-1`.T, W.`1`.T], Fraction[W.` 0`.T, W.`1`.T]]]
    the[Add.Aux[Fraction[W.`1`.T, W.`1`.T], Fraction[W.` 1`.T, W.`1`.T], Fraction[W.` 2`.T, W.`1`.T]]]
  }

  it should "subtract" in {
    the[Subtract.Aux[Fraction[W.`2`.T, W.`3`.T], Fraction[W.`-1`.T, W.`3`.T], Fraction[W.`1`.T, W.`1`.T]]]
  }

  it should "multiply" in {
    the[Multiply.Aux[Fraction[W.`2`.T, W.`3`.T], Fraction[W.`4`.T, W.`3`.T], Fraction[W.`8`.T, W.`9`.T]]]
  }
}

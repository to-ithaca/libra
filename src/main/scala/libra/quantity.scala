package libra

import ops.quantity._
import shapeless._
import spire._, spire.algebra._, spire.math._, spire.implicits._

/** Represents a dimensional quantity
  * 
  * @tparam A the Numeric type of the quantity e.g. Int, Float, Double
  * @tparam D the dimensions
  * @param value the coefficient
  *
  * @example
  * {{{
  * scala> import spire.implicits._
  * scala> import libra._, libra.si._
  * 
  * scala> Quantity[Double, Term[Length, Metre, Fraction[1, 1]] :: HNil](5.5) // represents 5.5 m 
  * scala> res0: Quantity[Double, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(5.5)
  * }}}
  */
case class Quantity[A, D <: HList](val value: A) extends AnyVal {

 /**
   * Adds another quantity using the spire AdditiveSemigroup.
   * @param q1 the quantity to add.  This must have the equivalient dimensions.
   *
   * {{{
   * scala> import spire.implicits._
   * scala> import shapeless._
   * scala> import libra._, libra.si._
   * scala> 3.m add 2.m
   * res1: Quantity[Int, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(5)
   * }}}
   */
  def add[D1 <: HList](q1: Quantity[A, D1])(implicit a: Add[Quantity[A, D], Quantity[A, D1]]): a.Out = a(this, q1)

  /** Negates the quantity
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> val x = 2.m
    * x: Quantity[Int, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(2)
    * scala> x.negate
    * res2: Quantity[Int, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(-2)
    * }}}
    */
  def negate()(implicit ev: AdditiveGroup[A]): Quantity[A, D] = Quantity(-value)

 /**
   * Subtracts another quantity using the spire AdditiveGroup
   * @param q1 the quantity to subtract.  This must have the equivalient dimensions.
   *
   * {{{
   * scala> import spire.implicits._
   * scala> import shapeless._
   * scala> import libra._, libra.si._
   * scala> 3.m subtract 2.m
   * res1: Quantity[Int, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(1)
   * }}}
   */
  def subtract[D1 <: HList](q1: Quantity[A, D1])(implicit a: Add[Quantity[A, D], Quantity[A, D1]], group: AdditiveGroup[A]): a.Out = a(this, q1.negate)

  /** Raises the quantity to the power of -1
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> val x = 2.0.m
    * x: Quantity[Double, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(2.0)
    * scala> x.invert
    * res2: Quantity[Double, Term[Length, Metre, Fraction[-1, 1]] :: HNil] = Quantity(0.5)
    * }}}
    */
  def invert()(implicit i: Invert[Quantity[A, D]]): i.Out = i(this)


  /** Multiplies by a quantity
    * 
    * @param q1 the quantity to multiply by
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.m multiply 3.m
    * res0: Quantity[Int, Term[Length, Metre, Fraction[2, 1]] :: HNil] = Quantity(6)
    * }}}
    */
  def multiply[D1 <: HList](q1: Quantity[A, D1])(implicit m: Multiply[Quantity[A, D], Quantity[A, D1]]): m.Out = m(this, q1)

  /** Divides by a quantity
    * 
    * @param q1 the quantity to divide by
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 4.0.m divide 2.0.m
    * res0: Quantity[Double, HNil] = Quantity(2.0)
    * }}}
    */
  def divide[D1 <: HList](q1: Quantity[A, D1])(implicit d: Divide[Quantity[A, D], Quantity[A, D1]]): d.Out = d(this, q1)

  /** Raises to a power
    * 
    * @tparam P the Integer power to raise by
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.0.m.power[3]
    * res0: Quantity[Double, Term[Length, Metre, Fraction[3, 1]] :: HNil] = Quantity(8.0)
    * }}}
    */
  def power[P <: Singleton with Int]()(implicit p: Power[Quantity[A, D], P]): p.Out = p(this)

  /** The standard index form String
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import libra._, libra.si._
    * scala> 2.m.show
    * res0: String = 2 m [L]
    * }}}
    */
  def show()(implicit s: Show[Quantity[A, D]]): String = s(this)

  /** Alias for add
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 3.m + 2.m
    * res1: Quantity[Int, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(5)
    * }}}
    */
  def +[D1 <: HList](q1: Quantity[A, D1])(implicit a: Add[Quantity[A, D], Quantity[A, D1]]): a.Out = add(q1)

  /** Alias for subtract
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 3.m - 2.m
    * res1: Quantity[Int, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(1)
    * }}}
    */
  def -[D1 <: HList](q1: Quantity[A, D1])(implicit a: Add[Quantity[A, D], Quantity[A, D1]], g: AdditiveGroup[A]): a.Out = subtract(q1)

  /** Alias for multiply
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.m * 3.m
    * res0: Quantity[Int, Term[Length, Metre, Fraction[2, 1]] :: HNil] = Quantity(6)
    * }}}
    */
  def *[D1 <: HList](q1: Quantity[A, D1])(implicit m: Multiply[Quantity[A, D], Quantity[A, D1]]): m.Out = multiply(q1)


  /** Alias for divide
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 4.0.m / 2.0.m
    * res0: Quantity[Double, HNil] = Quantity(2.0)
    * }}}
    */
  def /[D1 <: HList](q1: Quantity[A, D1])(implicit d: Divide[Quantity[A, D], Quantity[A, D1]]): d.Out = divide(q1)

  /** Alias for power
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.0.m^(3)
    * res0: Quantity[Double, Term[Length, Metre, Fraction[3, 1]] :: HNil] = Quantity(8.0)
    * }}}
    */
  def ^[P <: Singleton with Int](pow: P)(implicit p: Power[Quantity[A, D], P]): p.Out = p(this)

  def to[U <: Unit[_]](implicit to: ConvertTo[Quantity[A, D], U]): to.Out = to(this)
}

object Quantity {

  implicit def quantityModule[A, D <: HList](implicit R: Ring[A], ev: shapeless.ops.hlist.Align[D, D]): Module[Quantity[A, D], A] = {
    new Module[Quantity[A, D], A] {

      implicit def scalar: Rng[A] = R

      def negate(x: Quantity[A,D]): Quantity[A,D] = x.negate
      def zero: Quantity[A,D] = Quantity(R.zero)
      def plus(x: Quantity[A,D], y: Quantity[A,D]): Quantity[A,D] = x add y
      def timesl(r: A, v: Quantity[A,D]): Quantity[A,D] = Quantity(r * v.value)

    }
  }
}

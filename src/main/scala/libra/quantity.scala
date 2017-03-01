package libra

import ops.quantity._
import spire._, spire.algebra._, spire.math._, spire.implicits._

/** Represents a quantity in standard index form
  * 
  * @tparam A the Numeric type of the quantity e.g. Int, Float, Double
  * @tparam I the standard index form exponent
  * @tparam D the dimensions
  * @param value the standard form coefficient
  *
  * @example
  * {{{
  * scala> import spire.implicits._
  * scala> import libra._, libra.si._
  * 
  * scala> Quantity[Double, 3, Dimensions[Length :: HNil, HNil]](5.5) // represents 5.5 x 10^3 m 
  * scala> res0: Quantity[Double, 3, Dimensions[Length :: HNil, HNil]] = Quantity(5.5)
  * }}}
  */
case class Quantity[A, I <: Singleton with Int, D <: Dimensions[_, _]](val value: A) {

 /**
   * Adds another quantity using the spire AdditiveSemigroup.
   * @param q1 the quantity to add.  This must have the equivalient dimensions.
   *
   * {{{
   * scala> import spire.implicits._
   * scala> import shapeless._
   * scala> import libra._, libra.si._
   * scala> 3.m add 2.m
   * res1: Quantity[Int, 0, Dimensions[Length :: HNil, HNil]] = Quantity(5)
   * }}}
   */
  def add[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit a: Add[Quantity[A, I, D], Quantity[A, I1, D1]]): a.Out = a(this, q1)

  /** Negates the quantity
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> val x = 2.m
    * x: Quantity[Int, 0, Dimensions[Length :: HNil, HNil]] = Quantity(2)
    * scala> x.negate
    * res2: Quantity[Int, 0, Dimensions[Length :: HNil, HNil]] = Quantity(-2)
    * }}}
    */
  def negate()(implicit ev: AdditiveGroup[A]): Quantity[A, I, D] = Quantity(-value)

 /**
   * Subtracts another quantity using the spire AdditiveGroup
   * @param q1 the quantity to subtract.  This must have the equivalient dimensions.
   *
   * {{{
   * scala> import spire.implicits._
   * scala> import shapeless._
   * scala> import libra._, libra.si._
   * scala> 3.m subtract 2.m
   * res1: Quantity[Int, 0, Dimensions[Length :: HNil, HNil]] = Quantity(1)
   * }}}
   */
  def subtract[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit a: Add[Quantity[A, I, D], Quantity[A, I1, D1]], group: AdditiveGroup[A]): a.Out = a(this, q1.negate)

  /**
    * Sets the exponent in standard index form, without changing the true value of the quantity.
    * This is useful for avoiding rounding errors when manipulating [[value]].
    * 
    * @tparam I1 the power of 10 to convert to
    * 
    * For example, 5000.m.exp[3] changes the representation of 5000.0 x 10^0 m to 5.0 x 10^3 m:
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> val x = 5000.0.m
    * x: Quantity[Double, 0, Dimensions[Length :: HNil, HNil]] = Quantity(5000.0)
    * scala> x.exp[3]
    * res2: Quantity[Double, 3, Dimensions[Length :: HNil, HNil]] = Quantity(5.0)
    * }}}
    */
  def exp[I1 <: Singleton with Int]()(implicit p: Exp[Quantity[A, I, D], I1]): p.Out = p(this)

  /** Raises the quantity to the power of -1
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> val x = 2.0.m
    * x: Quantity[Double, 0, Dimensions[Length :: HNil, HNil]] = Quantity(2.0)
    * scala> x.invert
    * res2: Quantity[Double, 0, Dimensions[HNil, Length :: HNil]] = Quantity(0.5)
    * }}}
    */
  def invert()(implicit i: Invert[Quantity[A, I, D]]): i.Out = i(this)


  /** Multiplies by a quantity
    * 
    * @param q1 the quantity to multiply by
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.m multiply 3.m
    * res0: Quantity[Int, 0, Dimensions[Length :: Length :: HNil, HNil]] = Quantity(6)
    * }}}
    */
  def multiply[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit m: Multiply[Quantity[A, I, D], Quantity[A, I1, D1]]): m.Out = m(this, q1)

  /** Divides by a quantity
    * 
    * @param q1 the quantity to divide by
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 4.0.m divide 2.0.m
    * res0: Quantity[Double, 0, Dimensions[HNil, HNil]] = Quantity(2.0)
    * }}}
    */
  def divide[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit d: Divide[Quantity[A, I, D], Quantity[A, I1, D1]]): d.Out = d(this, q1)

  /** Raises to a power
    * 
    * @tparam P the Integer power to raise by
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.m.power[3]
    * res0: Quantity[Int, 0, Dimensions[Length :: Length :: Length :: HNil, HNil]] = Quantity(8)
    * }}}
    */
  def power[P <: Singleton with Int]()(implicit p: Power[Quantity[A, I, D], P]): p.Out = p(this)

  /** The standard index form String
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import libra._, libra.si._
    * scala> 2.m.show
    * res0: String = 2 * 10^0 m^1 [L^1]
    * }}}
    */
  def show()(implicit s: Show[Quantity[A, I, D]]): String = s(this)

  /** The value of the quantity in base units
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import libra._, libra.si._
    * scala> 3.km.baseValue
    * res0: Int = 3000
    * }}}
    */
  def baseValue()(implicit v: ValueOf[I], C: ConvertableTo[A], M: MultiplicativeSemigroup[A]): A = value * C.fromDouble(math.pow(10.0, v.value.toDouble))

  /** Alias for add
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 3.m + 2.m
    * res1: Quantity[Int, 0, Dimensions[Length :: HNil, HNil]] = Quantity(5)
    * }}}
    */
  def +[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit a: Add[Quantity[A, I, D], Quantity[A, I1, D1]]): a.Out = add(q1)

  /** Alias for subtract
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 3.m - 2.m
    * res1: Quantity[Int, 0, Dimensions[Length :: HNil, HNil]] = Quantity(1)
    * }}}
    */
  def -[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit a: Add[Quantity[A, I, D], Quantity[A, I1, D1]], g: AdditiveGroup[A]): a.Out = subtract(q1)

  /** Alias for multiply
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.m * 3.m
    * res0: Quantity[Int, 0, Dimensions[Length :: Length :: HNil, HNil]] = Quantity(6)
    * }}}
    */
  def *[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit m: Multiply[Quantity[A, I, D], Quantity[A, I1, D1]]): m.Out = multiply(q1)


  /** Alias for divide
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 4.0.m / 2.0.m
    * res0: Quantity[Double, 0, Dimensions[HNil, HNil]] = Quantity(2.0)
    * }}}
    */
  def /[I1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[A, I1, D1])(implicit d: Divide[Quantity[A, I, D], Quantity[A, I1, D1]]): d.Out = divide(q1)

  /** Alias for power
    * 
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra._, libra.si._
    * scala> 2.m^(3)
    * res0: Quantity[Int, 0, Dimensions[Length :: Length :: Length :: HNil, HNil]] = Quantity(8)
    * }}}
    */
  def ^[P <: Singleton with Int](pow: P)(implicit p: Power[Quantity[A, I, D], P]): p.Out = p(this)
}

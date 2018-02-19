package libra

import ops.quantity._
import shapeless._
import shapeless.ops.hlist.Align
import spire.algebra._, spire.implicits._

/** Represents a dimensional quantity
  *
  * @tparam A the Numeric type of the quantity e.g. Int, Float, Double
  * @tparam D the dimensions
  * @param value the coefficient
  *
  * @example
  * {{{
  * scala> import spire.implicits._
  * scala> import libra.implicits._
  *
  * scala> Quantity[Double, Term[Length, Metre, Fraction[1, 1]] :: HNil](5.5) // represents 5.5 m
  * scala> res0: Quantity[Double, Term[Length, Metre, Fraction[1, 1]] :: HNil] = Quantity(5.5)
  * }}}
  */
case class Quantity[A, D <: HList](value: A) extends AnyVal {

  /**
    * Aligns the dimensions of a quantity.
    *
    * Two quantities may have the same dimension, but have different parameter orders within their HLists. This reorders the
    * dimensions such that the quantites have the same type.  This is useful for algebra typeclasses which expect parameters
    * to have the same type.
    *
    * @tparam D1 A dimension with the same parameters but a different parameter order
    *
    * {{{
    * scala> import shapeless._
    * scala> import spire.implicits._
    * scala> import libra.implicits._
    *
    * scala> type MetreKilogram = Term[Length, Metre, Fraction[1, 1]] :: Term[Mass, Kilogram, Fraction[1, 1]] :: HNil
    * scala> type KilogramMetre = Term[Mass, Kilogram, Fraction[1, 1]] :: Term[Length, Metre, Fraction[1, 1]] ::  HNil
    *
    * scala> val q0: Quantity[Double, MetreKilogram] = Quantity(2.0)
    * scala> val q1: Quantity[Double, KilogramMetre] = Quantity(3.0)
    *
    * // uses spire's Signed typeclass
    * scala> q0 compare q1.as[MetreKilogram]
    * res0: Int = -1
    * }}}
    */
  def as[D1 <: HList](implicit ev: Align[D, D1]): Quantity[A, D1] = Quantity(value)

  /**
    * Adds another quantity using the spire AdditiveSemigroup.
    * @param q1 the quantity to add.  This must have the equivalient dimensions.
    *
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra.implicits._
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
    * scala> import libra.implicits._
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
   * scala> import libra.implicits._
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
    * scala> import libra.implicits._
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
    * scala> import libra.implicits._
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
    * scala> import libra.implicits._
    * scala> 4.0.m divide 2.0.m
    * res0: Quantity[Double, HNil] = Quantity(2.0)
    * }}}
    */
  def divide[D1 <: HList](q1: Quantity[A, D1])(implicit d: Divide[Quantity[A, D], Quantity[A, D1]]): d.Out = d(this, q1)


  /** Euclidean division by a quantity
    *
    * @param q1 the quantity to divide by
    * @return the quotient of Euclidean division
    *
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra.implicits._
    * scala> 5.m quotient 2.m
    * res0: Quantity[Int, HNil] = Quantity(2)
    * }}}
    */
  def quotient[D1 <: HList](q1: Quantity[A, D1])(implicit d: EuclideanDivide[Quantity[A, D], Quantity[A, D1]]): d.Out = d(this, q1)

  /** Raises to a power
    *
    * @tparam P the Integer power to raise by
    *
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra.implicits._
    * scala> 2.0.m.power[3]
    * res0: Quantity[Double, Term[Length, Metre, Fraction[3, 1]] :: HNil] = Quantity(8.0)
    * }}}
    */
  def power[P <: Singleton with Int]()(implicit p: Power[Quantity[A, D], P]): p.Out = p(this)

  /** The standard index form String
    *
    * {{{
    * scala> import spire.implicits._
    * scala> import libra.implicits._
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
    * scala> import libra.implicits._
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
    * scala> import libra.implicits._
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
    * scala> import libra.implicits._
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
    * scala> import libra.implicits._
    * scala> 4.0.m / 2.0.m
    * res0: Quantity[Double, HNil] = Quantity(2.0)
    * }}}
    */
  def /[D1 <: HList](q1: Quantity[A, D1])(implicit d: Divide[Quantity[A, D], Quantity[A, D1]]): d.Out = divide(q1)

  /** Alias for quotient
    *
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra.implicits._
    * scala> 5.m /~ 2.m
    * res0: Quantity[Int, HNil] = Quantity(2)
    * }}}
    */
  def /~[D1 <: HList](q1: Quantity[A, D1])(implicit d: EuclideanDivide[Quantity[A, D], Quantity[A, D1]]): d.Out = quotient(q1)


  /** Alias for power
    *
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra.implicits._
    * scala> 2.0.m^(3)
    * res0: Quantity[Double, Term[Length, Metre, Fraction[3, 1]] :: HNil] = Quantity(8.0)
    * }}}
    */
  def ^[P <: Singleton with Int](pow: P)(implicit p: Power[Quantity[A, D], P]): p.Out = p(this)

  /** Converts a quantity from one unit of measure to another
    *
    * @tparam U The unit of measure to convert to
    *
    * {{{
    * scala> import spire.implicits._
    * scala> import shapeless._
    * scala> import libra.implicits._
    * scala> 2.0.m.to[Centimetre]
    * res0: Quantity[Double, Term[Length, Centimetre, Fraction[1, 1]] :: HNil] = Quantity(200.0)
    * }}}
    *
    */
  def to[U <: UnitOfMeasure[_]](implicit to: ConvertTo[Quantity[A, D], U]): to.Out = to(this)

}

object Quantity {

  implicit def quantityModule[A, D <: HList](implicit R: Ring[A], ev: Align[D, D]): Module[Quantity[A, D], A] = {
    new Module[Quantity[A, D], A] {

      implicit def scalar: Rng[A] = R

      def negate(x: Quantity[A,D]): Quantity[A,D] = x.negate
      def zero: Quantity[A,D] = Quantity(R.zero)
      def plus(x: Quantity[A,D], y: Quantity[A,D]): Quantity[A,D] = x add y
      def timesl(r: A, v: Quantity[A,D]): Quantity[A,D] = Quantity(r * v.value)

    }
  }

  implicit def quantityEq[A, D <: HList](implicit E: Eq[A]): Eq[Quantity[A, D]] =
    new Eq[Quantity[A, D]] {
      def eqv(x: Quantity[A, D], y: Quantity[A, D]): Boolean = x.value === y.value
    }

  implicit def quantityOrder[A, D <: HList](implicit O: Order[A]): Order[Quantity[A, D]] =
    new Order[Quantity[A, D]] {
      def compare(x: Quantity[A, D], y: Quantity[A, D]): Int = x.value compare y.value
    }

  implicit def quantityPartialOrder[A, D <: HList](implicit O: PartialOrder[A]): PartialOrder[Quantity[A, D]] =
    new PartialOrder[Quantity[A, D]] {
      def partialCompare(x: Quantity[A, D], y: Quantity[A, D]): Double = x.value partialCompare y.value
    }

  implicit def quantitySigned[A, D <: HList](implicit O: Signed[A]): Signed[Quantity[A, D]] =
    new Signed[Quantity[A, D]] {
      def compare(x: Quantity[A, D], y: Quantity[A, D]): Int = x.value compare y.value
      def signum(q: Quantity[A, D]): Int = q.value.signum
      def abs(q: Quantity[A, D]): Quantity[A, D] = Quantity(q.value.abs)
    }

  implicit def quantityAdditiveSemigroup[A, D <: HList](implicit O: AdditiveSemigroup[A]): AdditiveSemigroup[Quantity[A, D]] =
    new AdditiveSemigroup[Quantity[A, D]] {
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(O.plus(x.value, y.value))
    }

  implicit def quantityAdditiveMonoid[A, D <: HList](implicit O: AdditiveMonoid[A]): AdditiveMonoid[Quantity[A, D]] =
    new AdditiveMonoid[Quantity[A, D]] {
      def zero: Quantity[A, D] = Quantity(O.zero)
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(O.plus(x.value, y.value))
    }

  implicit def quantityAdditiveGroup[A, D <: HList](implicit O: AdditiveGroup[A]): AdditiveGroup[Quantity[A, D]] =
    new AdditiveGroup[Quantity[A, D]] {
      def zero: Quantity[A, D] = Quantity(O.zero)
      def negate(q: Quantity[A, D]): Quantity[A, D] = Quantity(O.negate(q.value))
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(x.value + y.value)
    }

  implicit def quantityAdditiveCSemigroup[A, D <: HList](implicit O: AdditiveCSemigroup[A]): AdditiveCSemigroup[Quantity[A, D]] =
    new AdditiveCSemigroup[Quantity[A, D]] {
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(O.plus(x.value, y.value))
    }

  implicit def quantityAdditiveCMonoid[A, D <: HList](implicit O: AdditiveCMonoid[A]): AdditiveCMonoid[Quantity[A, D]] =
    new AdditiveCMonoid[Quantity[A, D]] {
      def zero: Quantity[A, D] = Quantity(O.zero)
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(O.plus(x.value, y.value))
    }

  implicit def quantityAdditiveAbGroup[A, D <: HList](implicit O: AdditiveAbGroup[A]): AdditiveAbGroup[Quantity[A, D]] =
    new AdditiveAbGroup[Quantity[A, D]] {
      def zero: Quantity[A, D] = Quantity(O.zero)
      def negate(q: Quantity[A, D]): Quantity[A, D] = Quantity(O.negate(q.value))
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(x.value + y.value)
    }

  implicit def quantityMetricSpace[A, R, D <: HList](implicit O: MetricSpace[A, R]): MetricSpace[Quantity[A, D], R] =
    new MetricSpace[Quantity[A, D], R] {
      def distance(x: Quantity[A, D], y: Quantity[A, D]): R = O.distance(x.value, y.value)
    }

  implicit def quantityCoordinateSpace[A, R, D <: HList](implicit O: CoordinateSpace[A, R]): CoordinateSpace[Quantity[A, D], R] =
    new CoordinateSpace[Quantity[A, D], R] {
      implicit def scalar: Field[R] = O.scalar
      def zero: Quantity[A, D] = Quantity(O.zero)
      def negate(q: Quantity[A, D]): Quantity[A, D] = Quantity(O.negate(q.value))
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(x.value + y.value)
      def axis(i: Int): Quantity[A, D] = Quantity(O.axis(i))
      def coord(q: Quantity[A, D], i: Int): R = O.coord(q.value, i)
      def dimensions: Int = O.dimensions
      def timesl(r: R, q: Quantity[A,D]): Quantity[A,D] = Quantity(O.timesl(r, q.value))
    }

  implicit def quantityVectorSpace[A, R, D <: HList](implicit O: VectorSpace[A, R]): VectorSpace[Quantity[A, D], R] =
    new VectorSpace[Quantity[A, D], R] {
      implicit def scalar: Field[R] = O.scalar
      def zero: Quantity[A, D] = Quantity(O.zero)
      def negate(q: Quantity[A, D]): Quantity[A, D] = Quantity(O.negate(q.value))
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(x.value + y.value)
      def timesl(r: R, q: Quantity[A,D]): Quantity[A,D] = Quantity(O.timesl(r, q.value))
    }

  implicit def quantityInnerProductSpace[A, R, D <: HList](implicit O: InnerProductSpace[A, R]): InnerProductSpace[Quantity[A, D], R] =
    new InnerProductSpace[Quantity[A, D], R] {
      implicit def scalar: Field[R] = O.scalar
      def zero: Quantity[A, D] = Quantity(O.zero)
      def negate(q: Quantity[A, D]): Quantity[A, D] = Quantity(O.negate(q.value))
      def plus(x: Quantity[A, D], y: Quantity[A, D]): Quantity[A, D] = Quantity(x.value + y.value)
      def timesl(r: R, q: Quantity[A,D]): Quantity[A,D] = Quantity(O.timesl(r, q.value))
      def dot(x: Quantity[A, D], y: Quantity[A, D]): R = O.dot(x.value, y.value)
    }
}

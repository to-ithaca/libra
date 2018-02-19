package libra

import org.scalatest._
import shapeless._
import spire.algebra._, spire.math._, spire.laws._, spire.laws.arb._
import org.typelevel.discipline.scalatest.Discipline
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Arbitrary._

class QuantityLawSpec extends FunSuite with Discipline {

  implicit def quantityArbitrary[A, D <: HList](
      implicit ev: Arbitrary[A]): Arbitrary[Quantity[A, D]] =
    Arbitrary(arbitrary[A].map(Quantity(_)))

  {
    import spire.implicits._
    import spire.std.SeqCoordinateSpace

    implicitly[PartialOrder[Quantity[Double, HNil]]]
    implicitly[Order[Quantity[Double, HNil]]]
    implicitly[Eq[Quantity[Double, HNil]]]
    implicitly[Signed[Quantity[Double, HNil]]]
    implicitly[Module[Quantity[Double, HNil], Double]]
    implicitly[AdditiveSemigroup[Quantity[Double, HNil]]]
    implicitly[AdditiveMonoid[Quantity[Double, HNil]]]
    implicitly[AdditiveGroup[Quantity[Double, HNil]]]
    implicitly[AdditiveCMonoid[Quantity[Double, HNil]]]
    implicitly[AdditiveAbGroup[Quantity[Double, HNil]]]
    implicitly[AdditiveCSemigroup[Quantity[Double, HNil]]]
    implicitly[MetricSpace[Quantity[BigInt, HNil], BigInt]]
    implicit val seqCoordinateSpace: SeqCoordinateSpace[Float, Seq[Float]] =
      new SeqCoordinateSpace(dimensions = 3)
    implicitly[CoordinateSpace[Quantity[Seq[Float], HNil], Float]]
    implicitly[InnerProductSpace[Quantity[Seq[Float], HNil], Float]]
    implicitly[VectorSpace[Quantity[Seq[Float], HNil], Float]]
  }

  {
    implicit val intIsOrder: Order[Int] = spire.std.int.IntAlgebra
    checkAll("Order[Quantity[Int, HNil]].order",
             OrderLaws[Quantity[Int, HNil]].order)
  }
  {
    implicit val intIsPartialOrder: PartialOrder[Int] = spire.std.int.IntAlgebra
    checkAll("Order[Quantity[Int, HNil]].partialOrder",
             OrderLaws[Quantity[Int, HNil]].partialOrder)
  }

  {
    implicit val intIsAdditiveSemigroup: AdditiveSemigroup[Int] =
      spire.std.int.IntAlgebra
    implicit val intIsEq: Eq[Int] = spire.std.int.IntAlgebra
    checkAll("Group[Quantity[Int, HNil]].additiveSemigroup",
             GroupLaws[Quantity[Int, HNil]].additiveSemigroup)
  }

  {
    implicit val intIsAdditiveMonoid: AdditiveMonoid[Int] =
      spire.std.int.IntAlgebra
    implicit val intIsEq: Eq[Int] = spire.std.int.IntAlgebra
    checkAll("Group[Quantity[Int, HNil]].additiveMonoid",
             GroupLaws[Quantity[Int, HNil]].additiveMonoid)
  }

  {
    implicit val intIsAdditiveGroup: AdditiveGroup[Int] =
      spire.std.int.IntAlgebra
    implicit val intIsEq: Eq[Int] = spire.std.int.IntAlgebra
    checkAll("Group[Quantity[Int, HNil]].additiveGroup",
             GroupLaws[Quantity[Int, HNil]].additiveGroup)
  }

  {
    implicit val intIsAdditiveCMonoid: AdditiveCMonoid[Int] =
      spire.std.int.IntAlgebra
    implicit val intIsEq: Eq[Int] = spire.std.int.IntAlgebra
    checkAll("Group[Quantity[Int, HNil]].additiveCMonoid",
             GroupLaws[Quantity[Int, HNil]].additiveCMonoid)
  }

  {
    implicit val intIsAdditiveAbGroup: AdditiveAbGroup[Int] =
      spire.std.int.IntAlgebra
    implicit val intIsEq: Eq[Int] = spire.std.int.IntAlgebra
    checkAll("Group[Quantity[Int, HNil]].additiveAbGroup",
             GroupLaws[Quantity[Int, HNil]].additiveAbGroup)
  }

  {
    implicit val stringIsMetricSpace: MetricSpace[String, Int] =
      spire.std.string.levenshteinDistance
    implicit val stringIsEq: Eq[String] = spire.std.string.StringOrder
    implicit val intIsSigned: Signed[Int] = spire.std.int.IntAlgebra
    implicit val intIsAdditiveSemigroup: AdditiveSemigroup[Int] =
      spire.std.int.IntAlgebra
    checkAll("Base[Quantity[String, HNil]].metricSpace",
             BaseLaws[Quantity[String, HNil]].metricSpace[Int])
  }

  {
    implicit val rationalIsField: Field[Rational] =
      spire.math.Rational.RationalAlgebra
    implicit val rationalIlist: Eq[Rational] =
      spire.math.Rational.RationalAlgebra
    implicit val listIsVectorSpace: VectorSpace[List[Rational], Rational] =
      spire.std.seq.SeqInnerProductSpace
    implicit val listIsEq: Eq[List[Rational]] =
      spire.optional.vectorOrder.seqOrder
    checkAll(
      "VectorSpace[Quantity[List[Rational], HNil], Rational].vectorSpace",
      VectorSpaceLaws[Quantity[List[Rational], HNil], Rational].vectorSpace)
  }

  {
    implicit val rationalIsField: Field[Rational] =
      spire.math.Rational.RationalAlgebra
    implicit val rationalIlist: Eq[Rational] =
      spire.math.Rational.RationalAlgebra
    implicit val rationalIsOrder: Order[Rational] =
      spire.math.Rational.RationalAlgebra
    implicit val rationalIsSigned: Signed[Rational] =
      spire.math.Rational.RationalAlgebra
    implicit val listIsVectorSpace
      : InnerProductSpace[List[Rational], Rational] =
      spire.std.seq.SeqInnerProductSpace
    implicit val listIsEq: Eq[List[Rational]] =
      spire.optional.vectorOrder.seqOrder
    checkAll(
      "VectorSpace[Quantity[List[Rational], HNil], Rational].innerProductSpace",
      VectorSpaceLaws[Quantity[List[Rational], HNil], Rational].innerProductSpace)
  }
}

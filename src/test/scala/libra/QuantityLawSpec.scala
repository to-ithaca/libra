package libra

import org.scalatest._

import shapeless._
import spire._, spire.algebra._, spire.math._, spire.implicits._, spire.laws._

import org.typelevel.discipline.scalatest.Discipline

import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Arbitrary._

class QuantityLawSpec extends FunSuite with Discipline {

  implicit def quantityArbitrary[A, D <: HList](implicit ev: Arbitrary[A]): Arbitrary[Quantity[A, D]] =
    Arbitrary(arbitrary[A].map(Quantity(_)))

  {
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
    implicit val seqCoordinateSpace: SeqCoordinateSpace[Float, Seq[Float]] = new SeqCoordinateSpace(dimensions = 3)
    implicitly[CoordinateSpace[Quantity[Seq[Float], HNil], Float]]
    implicitly[InnerProductSpace[Quantity[Seq[Float], HNil], Float]]
    implicitly[VectorSpace[Quantity[Seq[Float], HNil], Float]]
  }

  checkAll("Quantity[Int, HNil]", OrderLaws[Quantity[Int, HNil]].order)
}

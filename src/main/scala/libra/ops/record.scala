package libra
package ops

import shapeless._, shapeless.labelled.FieldType, shapeless.ops.hlist._

object recordExtra {

  trait Zip[L <: HList, R <: HList] {
    type Out
  }

  object Zip {
    type Aux[L <: HList, R <: HList, Out0 <: HList] = Zip[L, R] { type Out = Out0 }

    implicit def recordExtraZipNotInLeft[R <: HList]: Aux[HNil, R, R] = new Zip[HNil, R] { type Out = R }

    implicit def recordExtraZipInBoth[K, LV, RV, R <: HList, LTail <: HList, RTail <: HList, OutTail <: HList](
      implicit ev0: shapeless.ops.record.Selector.Aux[R, K, RV],
      ev1: FilterNot.Aux[R, FieldType[K, RV], RTail],
      ev2: Aux[LTail, RTail, OutTail]
    ): Aux[FieldType[K, LV] :: LTail, R, FieldType[K, (LV, RV)] :: OutTail] =
      new Zip[FieldType[K, LV] :: LTail, R] {
        type Out = FieldType[K, (LV, RV)] :: OutTail
      }

    implicit def recordExtraZipNotInRight[K, LV, R <: HList, LTail <: HList, OutTail <: HList](
      implicit ev0: Refute[shapeless.ops.record.Selector[R, K]],
      ev1: Aux[LTail, R, OutTail]
    ): Aux[FieldType[K, LV] :: LTail, R, FieldType[K, LV] :: OutTail] =
      new Zip[FieldType[K, LV] :: LTail, R] {
        type Out = FieldType[K, LV] :: OutTail
      }
  }
}


package libra
package ops

import shapeless._, shapeless.labelled.FieldType, shapeless.ops.hlist._,
shapeless.ops.record.Selector

/** Typeclass for merging two records
  *
  * Given a field with key K with value LV in L and value RV in R, this produces a FieldType[K, (LV, RV)]
  * Given a field with key K with value LV in L and no value in R, this produces a FieldType[K, LV]
  * Given a field with key K with no value in L and value RV in R, this produces a FieldType[K, RV]
  */
trait Merge[L <: HList, R <: HList] {
  type Out
}

object Merge {
  type Aux[L <: HList, R <: HList, Out0 <: HList] = Merge[L, R] {
    type Out = Out0
  }

  implicit def recordExtraMergeNotInLeft[R <: HList]: Aux[HNil, R, R] =
    new Merge[HNil, R] { type Out = R }

  implicit def recordExtraMergeInBoth[K,
                                      LV,
                                      RV,
                                      R <: HList,
                                      LTail <: HList,
                                      RTail <: HList,
                                      OutTail <: HList](
      implicit ev0: Selector.Aux[R, K, RV],
      ev1: FilterNot.Aux[R, FieldType[K, RV], RTail],
      ev2: Aux[LTail, RTail, OutTail]
  ): Aux[FieldType[K, LV] :: LTail, R, FieldType[K, (LV, RV)] :: OutTail] =
    new Merge[FieldType[K, LV] :: LTail, R] {
      type Out = FieldType[K, (LV, RV)] :: OutTail
    }

  implicit def recordExtraMergeNotInRight[K,
                                          LV,
                                          R <: HList,
                                          LTail <: HList,
                                          OutTail <: HList](
      implicit ev0: Refute[Selector[R, K]],
      ev1: Aux[LTail, R, OutTail]
  ): Aux[FieldType[K, LV] :: LTail, R, FieldType[K, LV] :: OutTail] =
    new Merge[FieldType[K, LV] :: LTail, R] {
      type Out = FieldType[K, LV] :: OutTail
    }
}

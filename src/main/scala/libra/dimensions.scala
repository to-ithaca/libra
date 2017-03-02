package libra

import shapeless._
/**
  * Represents the dimensions of a quantity, in base units
  * e.g. an acceleration of 3ms-2 in SI Units would have Dimensions[Length :: HNil, Time :: Time :: HNil]
  */
trait Dimensions[Numerator <: HList, Denominator <: HList]

object baseDimensions {
  type Length
  type Time
  type Mass

  implicit def lengthShowDimension: libra.ops.dimensions.ShowDimension[Length] = new libra.ops.dimensions.ShowDimension[Length] {
    def apply(): String = "L"
  }
  implicit def massShowDimension: libra.ops.dimensions.ShowDimension[Mass] = new libra.ops.dimensions.ShowDimension[Mass] {
    def apply(): String = "M"
  }
  implicit def timeShowDimension: libra.ops.dimensions.ShowDimension[Time] = new libra.ops.dimensions.ShowDimension[Time] {
    def apply(): String = "T"
  }
  implicit def lengthShowUnit: libra.ops.dimensions.ShowUnit[Length] = new libra.ops.dimensions.ShowUnit[Length] {
    def apply(): String = "m"
  }
  implicit def massShowUnit: libra.ops.dimensions.ShowUnit[Mass] = new libra.ops.dimensions.ShowUnit[Mass] {
    def apply(): String = "kg"
  }
  implicit def timeShowUnit: libra.ops.dimensions.ShowUnit[Time] = new libra.ops.dimensions.ShowUnit[Time] {
    def apply(): String = "s"
  }
}

package libra

import shapeless._
/**
  * Represents the dimensions of a quantity, in base units
  * 
  * An acceleration in SI Units would have Dimensions[Length :: HNil, Time :: Time :: HNil]
  * 
  * @tparam N a HList of base types in the numerator
  * @tparam D a HList of base types in the denominator
  */
trait Dimensions[N <: HList, D <: HList]

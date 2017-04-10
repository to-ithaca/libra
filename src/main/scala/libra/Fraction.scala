package libra

import singleton.ops._

/** Represents a fraction
  * 
  * @tparam N the numerator
  * @tparam D the denominator
  */
trait Fraction[N <: XInt, D <: XInt]

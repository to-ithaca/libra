package libra
package units

import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import libra.si.{Second, Time}
import shapeless._

package object angle extends AngleDegree
      with AngleArcminute
      with AngleArcsecond
      with AngleRadian
      with AngleGradian
    with AngleTurn {

  object degree extends AngleDegree
  object arcminute extends AngleArcminute
  object arcsecond extends AngleArcsecond
  object radian extends AngleRadian
  object gradian extends AngleGradian
  object turn extends AngleTurn
}

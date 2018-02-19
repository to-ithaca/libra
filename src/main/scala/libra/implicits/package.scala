package libra

import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import shapeless._

package object implicits extends AngleImplicits with AngularVelocityImplicits with TimeImplicits with LengthImplicits with MassImplicits with CurrentImplicits with TemperatureImplicits with AccelerationImplicits with VelocityImplicits with ForceImplicits with MomentumImplicits

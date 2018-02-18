package libra

import libra.ops.quantity.ConvertTo
import ops.base.{Conversion, ConversionFactor, Show}
import spire._
import spire.algebra._
import spire.math._
import spire.implicits._
import singleton.ops._
import shapeless._

package object implicits extends AngleImplicits with AngularVelocityImplicits with TimeImplicits with LengthImplicits with MassImplicits with CurrentImplicits with TemperatureImplicits {

  object angle extends AngleImplicits {

    object degree extends AngleDegreeImplicits
    object arcminute extends AngleArcminuteImplicits
    object arcsecond extends AngleArcsecondImplicits
    object radian extends AngleRadianImplicits
    object gradian extends AngleGradianImplicits
    object turn extends AngleTurnImplicits
  }

  object angularVelocity extends AngularVelocityImplicits {

    object degreesPerSecond extends AngularVelocityDegreesPerSecondImplicits
    object arcminutesPerSecond extends AngularVelocityArcminutesPerSecondImplicits
    object arcsecondsPerSecond extends AngularVelocityArcsecondsPerSecondImplicits
    object radiansPerSecond extends AngularVelocityRadiansPerSecondImplicits
    object gradiansPerSecond extends AngularVelocityGradiansPerSecondImplicits
    object turnsPerSecond extends AngularVelocityTurnsPerSecondImplicits
  }

  object time extends TimeImplicits {
    // TODO fill these in
  }

  object length extends LengthImplicits {
  }

  object mass extends MassImplicits {
  }

  object current extends CurrentImplicits {
  }

  object temperature extends TemperatureImplicits {
  }
}

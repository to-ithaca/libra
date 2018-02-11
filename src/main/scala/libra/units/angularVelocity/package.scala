package libra
package units

package object angularVelocity extends AngularVelocityDegreesPerSecond
      with AngularVelocityArcminutesPerSecond
      with AngularVelocityArcsecondsPerSecond
      with AngularVelocityRadiansPerSecond
      with AngularVelocityGradiansPerSecond
    with AngularVelocityTurnsPerSecond {

  object degreesPerSecond extends AngularVelocityDegreesPerSecond
  object arcminutesPerSecond extends AngularVelocityArcminutesPerSecond
  object arcsecondsPerSecond extends AngularVelocityArcsecondsPerSecond
  object radiansPerSecond extends AngularVelocityRadiansPerSecond
  object gradiansPerSecond extends AngularVelocityGradiansPerSecond
  object turnsPerSecond extends AngularVelocityTurnsPerSecond
}

package libra
package implicits

trait AngularVelocityImplicits {
  implicit final class AngularVelocityOps[A](val a: A) {
    def degreesPerSecond: AngularVelocityQuantity[A, Degree, Second] =
      Quantity(a)
    def arcminutesPerSecond: AngularVelocityQuantity[A, Arcminute, Second] =
      Quantity(a)
    def arcsecondsPerSecond: AngularVelocityQuantity[A, Arcsecond, Second] =
      Quantity(a)
    def radiansPerSecond: AngularVelocityQuantity[A, Radian, Second] =
      Quantity(a)
    def gradiansPerSecond: AngularVelocityQuantity[A, Gradian, Second] =
      Quantity(a)
    def turnsPerSecond: AngularVelocityQuantity[A, Turn, Second] = Quantity(a)
  }
}

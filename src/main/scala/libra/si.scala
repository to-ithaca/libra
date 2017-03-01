package libra

import ops.base._

/** SI units */
package object si {
  type Length
  type Mass
  type Time
  type Current
  type Temperature
  type Amount
  type Intensity

  implicit def lengthShowDimension: ShowDimension[Length] = ShowDimension[Length]("L")
  implicit def massShowDimension: ShowDimension[Mass] = ShowDimension[Mass]("M")
  implicit def timeShowDimension: ShowDimension[Time] = ShowDimension[Time]("T")
  implicit def currentShowDimension: ShowDimension[Current] = ShowDimension[Current]("I")
  implicit def temperatureShowDimension: ShowDimension[Temperature] = ShowDimension[Temperature]("Θ")
  implicit def amountShowDimension: ShowDimension[Amount] = ShowDimension[Amount]("N")
  implicit def intensityShowDimension: ShowDimension[Intensity] = ShowDimension[Intensity]("J")

  implicit def lengthShowUnit: ShowUnit[Length] = ShowUnit[Length]("m")
  implicit def massShowUnit: ShowUnit[Mass] = ShowUnit[Mass]("kg")
  implicit def timeShowUnit: ShowUnit[Time] = ShowUnit[Time]("s")
  implicit def currentShowUnit: ShowUnit[Current] = ShowUnit[Current]("A")
  implicit def temperatureShowUnit: ShowUnit[Temperature] = ShowUnit[Temperature]("K")
  implicit def amountShowUnit: ShowUnit[Amount] = ShowUnit[Amount]("mol")
  implicit def intensityShowUnit: ShowUnit[Intensity] = ShowUnit[Intensity]("cd")

  implicit final class BaseQuantityOps[A](val a: A) extends AnyVal {

    def km: QuantityOf[A, 3, Length] = Quantity(a)
    def m: QuantityOf[A, 0, Length] = Quantity(a)
    def cm: QuantityOf[A, -2, Length] = Quantity(a)
    def mm: QuantityOf[A, -3, Length] = Quantity(a)

    def kg: QuantityOf[A, 0, Mass] = Quantity(a)
    def g: QuantityOf[A, -3, Mass] = Quantity(a)
    def mg: QuantityOf[A, -6, Mass] = Quantity(a)

    def s: QuantityOf[A, 0, Time] = Quantity(a)
    def ms: QuantityOf[A, -3, Time] = Quantity(a)

    def A: QuantityOf[A, 0, Current] = Quantity(a)
    def mA: QuantityOf[A, -3, Current] = Quantity(a)
  }
}

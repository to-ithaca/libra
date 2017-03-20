package libra

import ops.base._
import spire._, spire.algebra._, spire.math._, spire.implicits._

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

    def km()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Length] = Quantity(a * C.fromInt(1000))
    def m: QuantityOf[A, Length] = Quantity(a)
    def cm()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Length] = Quantity(a * C.fromDouble(0.01))
    def mm()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Length] = Quantity(a * C.fromDouble(0.001))

    def kg: QuantityOf[A, Mass] = Quantity(a)
    def g()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Mass] = Quantity(a * C.fromDouble(0.001))
    def mg()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Mass] = Quantity(a * C.fromDouble(0.000001))

    def s: QuantityOf[A, Time] = Quantity(a)
    def ms()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Time] = Quantity(a * C.fromDouble(0.001))

    def A: QuantityOf[A, Current] = Quantity(a)
    def mA()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Current] = Quantity(a * C.fromDouble(0.001))
  }
}

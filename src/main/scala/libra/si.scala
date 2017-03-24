package libra

import ops.base.Show
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

  trait Metre extends Unit[Length]
  trait Kilogram extends Unit[Mass]
  trait Second extends Unit[Time]
  trait Ampere extends Unit[Current]
  trait Kelvin extends Unit[Temperature]
  trait Mole extends Unit[Amount]
  trait Candela extends Unit[Intensity]

  implicit def lengthShow: Show[Length] = Show[Length]("L")
  implicit def massShow: Show[Mass] = Show[Mass]("M")
  implicit def timeShow: Show[Time] = Show[Time]("T")
  implicit def currentShow: Show[Current] = Show[Current]("I")
  implicit def temperatureShow: Show[Temperature] = Show[Temperature]("Θ")
  implicit def amountShow: Show[Amount] = Show[Amount]("N")
  implicit def intensityShow: Show[Intensity] = Show[Intensity]("J")

  implicit def metreShow: Show[Metre] = Show[Metre]("m")
  implicit def kilogramShow: Show[Kilogram] = Show[Kilogram]("kg")
  implicit def secondShow: Show[Second] = Show[Second]("s")
  implicit def ampereShow: Show[Ampere] = Show[Ampere]("A")
  implicit def kelvinShow: Show[Kelvin] = Show[Kelvin]("K")
  implicit def moleShow: Show[Mole] = Show[Mole]("mol")
  implicit def candelaShow: Show[Candela] = Show[Candela]("cd")

  implicit final class BaseQuantityOps[A](val a: A) extends AnyVal {

    def km()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Length, Metre] = Quantity(a * C.fromInt(1000))
    def m: QuantityOf[A, Length, Metre] = Quantity(a)
    def cm()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Length, Metre] = Quantity(a * C.fromDouble(0.01))
    def mm()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Length, Metre] = Quantity(a * C.fromDouble(0.001))

    def kg: QuantityOf[A, Mass, Kilogram] = Quantity(a)
    def g()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Mass, Kilogram] = Quantity(a * C.fromDouble(0.001))
    def mg()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Mass, Kilogram] = Quantity(a * C.fromDouble(0.000001))

    def s: QuantityOf[A, Time, Second] = Quantity(a)
    def ms()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Time, Second] = Quantity(a * C.fromDouble(0.001))

    def A: QuantityOf[A, Current, Ampere] = Quantity(a)
    def mA()(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): QuantityOf[A, Current, Ampere] = Quantity(a * C.fromDouble(0.001))
  }
}

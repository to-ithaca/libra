package libra

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._

/** SI units */
package object si {
  type Length
  type Mass
  type Time
  type Current
  type Temperature
  type Amount
  type Intensity

  trait MetricUnit[I <: XInt, D] extends Unit[D]

  type Metre = MetricUnit[0, Length]
  type Centimetre = MetricUnit[-2, Length]
  type Millimetre = MetricUnit[-3, Length]
  type Kilometre = MetricUnit[3, Length]
  type Kilogram = MetricUnit[3, Mass]
  type Gram = MetricUnit[0, Mass]
  type Milligram = MetricUnit[-3, Mass]
  type Second = MetricUnit[0, Time]
  type Millisecond = MetricUnit[-3, Time]
  type Ampere = MetricUnit[0, Current]
  type MilliAmpere = MetricUnit[-3, Current]
  type Kelvin = Unit[Temperature]
  type Mole = Unit[Amount]
  type Candela = Unit[Intensity]

  implicit def lengthShow: Show[Length] = Show[Length]("L")
  implicit def massShow: Show[Mass] = Show[Mass]("M")
  implicit def timeShow: Show[Time] = Show[Time]("T")
  implicit def currentShow: Show[Current] = Show[Current]("I")
  implicit def temperatureShow: Show[Temperature] = Show[Temperature]("Θ")
  implicit def amountShow: Show[Amount] = Show[Amount]("N")
  implicit def intensityShow: Show[Intensity] = Show[Intensity]("J")

  implicit val zeroShow: Show[0] = Show("")
  implicit val centiShow: Show[-2] = Show("c")
  implicit val milliShow: Show[-3] = Show("m")
  implicit val killoShow: Show[3] = Show("k")

  implicit def metricLengthShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Length]] = Show(s"${s()}m")
  implicit def metricMassShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Mass]] = Show(s"${s()}g")
  implicit def metricTimeShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Time]] = Show(s"${s()}s")
  implicit def metricCurrentShow[I <: XInt](implicit s: Show[I]): Show[MetricUnit[I, Current]] = Show(s"${s()}A")

  implicit def kelvinShow: Show[Kelvin] = Show[Kelvin]("K")
  implicit def moleShow: Show[Mole] = Show[Mole]("mol")
  implicit def candelaShow: Show[Candela] = Show[Candela]("cd")

  implicit def metricConversion[A, D, PF <: XInt, PT <: XInt](
    implicit c: ConvertableTo[A],
    ev: Field[A],
    pf: ValueOf[PF],
    pt: ValueOf[PT]
  ): ConversionFactor[A, D, MetricUnit[PF, D], MetricUnit[PT, D]] = 
    new ConversionFactor(c.fromInt(10).pow(pf.value - pt.value))

  implicit final class BaseQuantityOps[A](val a: A) extends AnyVal {

    def km: QuantityOf[A, Length, Kilometre] = Quantity(a)
    def m: QuantityOf[A, Length, Metre] = Quantity(a)
    def cm: QuantityOf[A, Length, Centimetre] = Quantity(a)
    def mm: QuantityOf[A, Length, Millimetre] = Quantity(a)

    def kg: QuantityOf[A, Mass, Kilogram] = Quantity(a)
    def g: QuantityOf[A, Mass, Gram] = Quantity(a)
    def mg: QuantityOf[A, Mass, Milligram] = Quantity(a)

    def s: QuantityOf[A, Time, Second] = Quantity(a)
    def ms: QuantityOf[A, Time, Millisecond] = Quantity(a)

    def A: QuantityOf[A, Current, Ampere] = Quantity(a)
    def mA: QuantityOf[A, Current, MilliAmpere] = Quantity(a)
  }
}

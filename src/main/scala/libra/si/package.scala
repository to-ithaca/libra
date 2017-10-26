package libra

import ops.base.{Show, ConversionFactor}
import spire._, spire.algebra._, spire.math._, spire.implicits._
import singleton.ops._
import shapeless._

/** SI units */
package object si {
  type Length
  type Mass
  type Time
  type Current
  type Temperature
  type Amount
  type Intensity

  type Kilometre = MetricUnit[3, Length]
  type Metre = MetricUnit[0, Length]
  type Decimetre = MetricUnit[-1, Length]
  type Centimetre = MetricUnit[-2, Length]
  type Millimetre = MetricUnit[-3, Length]
  type Micrometre = MetricUnit[-6, Length]
  type Nanometre = MetricUnit[-9, Length]
  type Picometre = MetricUnit[-12, Length]
  type Femtometre = MetricUnit[-15, Length]
  type Attometre = MetricUnit[-18, Length]

  type Kilosecond = MetricUnit[3, Time]
  type Second = MetricUnit[0, Time]
  type Decisecond = MetricUnit[-1, Time]
  type Centisecond = MetricUnit[-2, Time]
  type Millisecond = MetricUnit[-3, Time]
  type Microsecond = MetricUnit[-6, Time]
  type Nanosecond = MetricUnit[-9, Time]
  type Picosecond = MetricUnit[-12, Time]
  type Femtosecond = MetricUnit[-15, Time]
  type Attosecond = MetricUnit[-18, Time]

  type Kilogram = MetricUnit[3, Mass]
  type Gram = MetricUnit[0, Mass]
  type Decigram = MetricUnit[-1, Mass]
  type Centigram = MetricUnit[-2, Mass]
  type Milligram = MetricUnit[-3, Mass]
  type Microgram = MetricUnit[-6, Mass]
  type Nanogram = MetricUnit[-9, Mass]
  type Picogram = MetricUnit[-12, Mass]
  type Femtogram = MetricUnit[-15, Mass]
  type Attogram = MetricUnit[-18, Mass]

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

  implicit val killoShow: Show[3] = Show("k")
  implicit val zeroShow: Show[0] = Show("")
  implicit val deciShow: Show[-1] = Show("d")
  implicit val centiShow: Show[-2] = Show("c")
  implicit val milliShow: Show[-3] = Show("m")
  implicit val microShow: Show[-6] = Show("μ")
  implicit val nanoShow: Show[-9] = Show("n")
  implicit val picoShow: Show[-12] = Show("p")
  implicit val femtoShow: Show[-15] = Show("f")
  implicit val attoShow: Show[-18] = Show("a")

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
    pf: SafeInt[PF],
    pt: SafeInt[PT]
  ): ConversionFactor[A, D, MetricUnit[PF, D], MetricUnit[PT, D]] = 
    new ConversionFactor(c.fromInt(10).pow(pf - pt))

  implicit final class SILengthQuantityOps[A](val a: A) extends AnyVal {
    def km: QuantityOf[A, Length, Kilometre] = Quantity(a)
    def m: QuantityOf[A, Length, Metre] = Quantity(a)
    def dm: QuantityOf[A, Length, Decimetre] = Quantity(a)
    def cm: QuantityOf[A, Length, Centimetre] = Quantity(a)
    def mm: QuantityOf[A, Length, Millimetre] = Quantity(a)
    def μm: QuantityOf[A, Length, Micrometre] = Quantity(a)
    def nm: QuantityOf[A, Length, Nanometre] = Quantity(a)
    def pm: QuantityOf[A, Length, Picometre] = Quantity(a)
    def fm: QuantityOf[A, Length, Femtometre] = Quantity(a)
    def am: QuantityOf[A, Length, Attometre] = Quantity(a)
  }

  type VelocityQuantity[A, L <: Unit[Length], T <: Unit[Time]] =
    Quantity[A, Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]

  type AccelerationQuantity[A, L <: Unit[Length], T <: Unit[Time]] =
    Quantity[A, Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-2, 1]] :: HNil]

  type MomentumQuantity[A, M <: Unit[Mass], L <: Unit[Length], T <: Unit[Time]] =
    Quantity[A, Term[Mass, M, Fraction[1, 1]] :: Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-1, 1]] :: HNil]

  type ForceQuantity[A, M <: Unit[Mass], L <: Unit[Length], T <: Unit[Time]] =
    Quantity[A, Term[Mass, M, Fraction[1, 1]] :: Term[Length, L, Fraction[1, 1]] :: Term[Time, T, Fraction[-2, 1]] :: HNil]

  implicit final class SIVelocityQuantityOps[A](val a: A) extends AnyVal {
    def kmps: VelocityQuantity[A, Kilometre, Second] = Quantity(a)
    def mps: VelocityQuantity[A, Metre, Second] = Quantity(a)
    def dmps: VelocityQuantity[A, Decimetre, Second] = Quantity(a)
    def cmps: VelocityQuantity[A, Centimetre, Second] = Quantity(a)
    def mmps: VelocityQuantity[A, Millimetre, Second] = Quantity(a)
    def μmps: VelocityQuantity[A, Micrometre, Second] = Quantity(a)
    def nmps: VelocityQuantity[A, Nanometre, Second] = Quantity(a)
    def pmps: VelocityQuantity[A, Picometre, Second] = Quantity(a)
    def fmps: VelocityQuantity[A, Femtometre, Second] = Quantity(a)
    def amps: VelocityQuantity[A, Attometre, Second] = Quantity(a)
  }

  implicit final class SIAccellerationQuantityOps[A](val a: A) extends AnyVal {
    def kmps2: AccelerationQuantity[A, Kilometre, Second] = Quantity(a)
    def mps2: AccelerationQuantity[A, Metre, Second] = Quantity(a)
    def dmps2: AccelerationQuantity[A, Decimetre, Second] = Quantity(a)
    def cmps2: AccelerationQuantity[A, Centimetre, Second] = Quantity(a)
    def mmps2: AccelerationQuantity[A, Millimetre, Second] = Quantity(a)
    def μmps2: AccelerationQuantity[A, Micrometre, Second] = Quantity(a)
    def nmps2: AccelerationQuantity[A, Nanometre, Second] = Quantity(a)
    def pmps2: AccelerationQuantity[A, Picometre, Second] = Quantity(a)
    def fmps2: AccelerationQuantity[A, Femtometre, Second] = Quantity(a)
    def amps2: AccelerationQuantity[A, Attometre, Second] = Quantity(a)
  }

  implicit final class SITimeQuantityOps[A](val a: A) extends AnyVal {
    def ks: QuantityOf[A, Time, Kilosecond] = Quantity(a)
    def s: QuantityOf[A, Time, Second] = Quantity(a)
    def ds: QuantityOf[A, Time, Decisecond] = Quantity(a)
    def cs: QuantityOf[A, Time, Centisecond] = Quantity(a)
    def ms: QuantityOf[A, Time, Millisecond] = Quantity(a)
    def μs: QuantityOf[A, Time, Microsecond] = Quantity(a)
    def ns: QuantityOf[A, Time, Nanosecond] = Quantity(a)
    def ps: QuantityOf[A, Time, Picosecond] = Quantity(a)
    def fs: QuantityOf[A, Time, Femtosecond] = Quantity(a)
    def as: QuantityOf[A, Time, Attosecond] = Quantity(a)
  }

  implicit final class SIMassQuantityOps[A](val a: A) extends AnyVal {
    def kg: QuantityOf[A, Mass, Kilogram] = Quantity(a)
    def g: QuantityOf[A, Mass, Gram] = Quantity(a)
    def dg: QuantityOf[A, Mass, Decigram] = Quantity(a)
    def cg: QuantityOf[A, Mass, Centigram] = Quantity(a)
    def mg: QuantityOf[A, Mass, Milligram] = Quantity(a)
    def μg: QuantityOf[A, Mass, Microgram] = Quantity(a)
    def ng: QuantityOf[A, Mass, Nanogram] = Quantity(a)
    def pg: QuantityOf[A, Mass, Picogram] = Quantity(a)
    def fg: QuantityOf[A, Mass, Femtogram] = Quantity(a)
    def ag: QuantityOf[A, Mass, Attogram] = Quantity(a)
  }

  implicit final class MomentumQuantityOps[A](val a: A) extends AnyVal {
    def kgmps: MomentumQuantity[A, Kilogram, Metre, Second] = Quantity(a)
    def Ns: MomentumQuantity[A, Kilogram, Metre, Second] = Quantity(a)
  }

  implicit final class ForceQuantityOps[A](val a: A) extends AnyVal {
    def N: ForceQuantity[A, Kilogram, Metre, Second] = Quantity(a)
  }


  implicit final class BaseQuantityOps[A](val a: A) extends AnyVal {
    def A: QuantityOf[A, Current, Ampere] = Quantity(a)
    def mA: QuantityOf[A, Current, MilliAmpere] = Quantity(a)
  }
}

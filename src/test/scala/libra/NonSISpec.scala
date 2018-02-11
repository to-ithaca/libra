// package libra

// import spire.implicits._
// import spire.math._
// import libra.nonsi._
// import libra.si._

// import org.scalatest._


// class NonSISpec extends WordSpec with Matchers {

//   val ε = 1E-9

//   "degree" should {
//     "show" in {
//       assert(2.degree.show === "2 degree [∠]")
//     }

//     "arcminute value" in {
//       assert(2.0.degree.to[Arcminute].value === 120.0)
//     }

//     "arcsecond value" in {
//       assert(2.0.degree.to[Arcsecond].value === 7200.0)
//     }

//     "radian value" in {
//       assert(180.0.degree.to[Radian].value === pi)
//     }

//     "gradian value" in {
//       assert(180.0.degree.to[Gradian].value === 200.0)
//     }

//     "turn value" in {
//       assert(720.0.degree.to[Turn].value === 2.0)
//     }
//   }

//   "arcminute" should {
//     "show" in {
//       assert(2.arcminute.show === "2 arcminute [∠]")
//     }

//     "degree value" in {
//       assert(120.0.arcminute.to[Degree].value === 2.0)
//     }

//     "arcsecond value" in {
//       assert(2.0.arcminute.to[Arcsecond].value === 120.0)
//     }

//     "radian value" in {
//       assert(10800.0.arcminute.to[Radian].value === pi)
//     }

//     "gradian value" in {
//       assert(10800.0.arcminute.to[Gradian].value === 200.0)
//     }

//     "turn value" in {
//       assert(21600.0.arcminute.to[Turn].value === 1.0)
//     }
//   }

//   "arcsecond" should {
//     "show" in {
//       assert(2.arcsecond.show === "2 arcsecond [∠]")
//     }

//     "degree value" in {
//       assert(7200.0.arcsecond.to[Degree].value === 2.0)
//     }

//     "arcminute value" in {
//       assert(120.0.arcsecond.to[Arcminute].value === 2.0)
//     }

//     "radian value" in {
//       assert(648000.0.arcsecond.to[Radian].value === pi)
//     }

//     "gradian value" in {
//       assert(648000.0.arcsecond.to[Gradian].value === 200.0 +- ε)
//     }

//     "turn value" in {
//       assert(1296000.0.arcsecond.to[Turn].value === (1.0) +- ε)
//     }
//   }

//   "degreesPerSecond" should {
//     "show" in {
//       assert(2.degreessPerSecond.show === "2 degree s^-1 [∠ T^-1]")
//     }

//     "arcminutesPerSecond value" in {
//       assert(2.0.degreessPerSecond.to[Arcminute].value === 120.0)
//     }

//     "arcsecondsPerSecond value" in {
//       assert(2.0.degreessPerSecond.to[Arcsecond].value === 7200.0)
//     }
//   }

//   "arcminutesPerSecond" should {
//     "show" in {
//       assert(2.arcminutesPerSecond.show === "2 arcminute s^-1 [∠ T^-1]")
//     }

//     "degreesPerSecond value" in {
//       assert(120.0.arcminutesPerSecond.to[Degree].value === 2.0)
//     }

//     "arcsecond value" in {
//       assert(2.0.arcminutesPerSecond.to[Arcsecond].value === 120.0)
//     }

//     "radian value" in {
//       assert(10800.0.arcminutesPerSecond.to[Radian].value === pi)
//     }
//   }

//   "arcsecondsPerSecond" should {
//     "show" in {
//       assert(2.arcsecondsPerSecond.show === "2 arcsecond s^-1 [∠ T^-1]")
//     }

//     "degreesPerSecond value" in {
//       assert(7200.0.arcsecondsPerSecond.to[Degree].value === 2.0)
//     }

//     "arcminutesPerSecond value" in {
//       assert(120.0.arcsecondsPerSecond.to[Arcminute].value === 2.0)
//     }

//     "radian value" in {
//       assert(648000.0.arcsecondsPerSecond.to[Radian].value === pi)
//     }

//   }

//   "radians" should {
//     "show" in {
//       assert(2.radian.show === "2 rad [∠]")
//     }

//     "degree value" in {
//       assert(pi.radian.to[Degree].value === 180.0)
//     }

//     "arcminute value" in {
//       assert(pi.radian.to[Arcminute].value === 10800.0)
//     }

//     "arcsecond value" in {
//       assert(pi.radian.to[Arcsecond].value === 648000.0)
//     }

//     "gradian value" in {
//       assert(pi.radian.to[Gradian].value === 200.0)
//     }

//     "turn value" in {
//       assert(pi.radian.to[Turn].value === 0.5 +- ε)
//     }
//   }

//   "radiansPerSecond" should {
//     "show" in {
//       assert(2.radiansPerSecond.show === "2 rad s^-1 [∠ T^-1]")
//     }

//     "degreesPerSecond value" in {
//       assert(pi.radiansPerSecond.to[Degree].value === 180.0)
//     }

//     "arcminutesPerSecond value" in {
//       assert(pi.radiansPerSecond.to[Arcminute].value === 10800.0)
//     }

//     "arcsecondsPerSecond value" in {
//       assert(pi.radiansPerSecond.to[Arcsecond].value === 648000.0)
//     }

//     "gradiansPerSecond value" in {
//       assert(pi.radiansPerSecond.to[Gradian].value === 200.0)
//     }

//     "turnsPerSecond value" in {
//       assert((2.0 * pi).radiansPerSecond.to[Turn].value === 1.0 +- ε)
//     }
//   }

//   "gradian" should {
//     "show" in {
//       assert(2.gradian.show === "2 gon [∠]")
//     }

//     "degree value" in {
//       assert(200.0.gradian.to[Degree].value === 180.0)
//     }

//     "radian value" in {
//       assert(200.0.gradian.to[Radian].value === pi +- ε)
//     }

//     "arcminute value" in {
//       assert(200.0.gradian.to[Arcminute].value === 10800.0)
//     }

//     "arcsecond value" in {
//       assert(200.0.gradian.to[Arcsecond].value === 648000.0 +- ε)
//     }

//     "turn value" in {
//       assert(400.0.gradian.to[Turn].value === 1)
//     }

//   }

//   "gradiansPerSecond" should {
//     "show" in {
//       assert(2.gradian.show === "2 gon [∠]")
//     }

//     "degreesPerSecond value" in {
//       assert(200.0.gradiansPerSecond.to[Degree].value === 180.0)
//     }

//     "radiansPerSecond value" in {
//       assert(200.0.gradiansPerSecond.to[Radian].value === pi +- ε)
//     }

//     "arcminutesPerSecond value" in {
//       assert(200.0.gradiansPerSecond.to[Arcminute].value === 10800.0)
//     }

//     "arcsecondsPerSecond value" in {
//       assert(200.0.gradiansPerSecond.to[Arcsecond].value === 648000.0 +- ε)
//     }

//     "turnsPerSecond value" in {
//       assert(400.0.gradiansPerSecond.to[Turn].value === 1.0)
//     }
//   }

//   "turn" should {
//     "show" in {
//       assert(2.turn.show === "2 tr [∠]")
//     }

//     "gradian value" in {
//       assert(1.0.turn.to[Gradian].value === 400.0)
//     }

//     "degree value" in {
//       assert(1.0.turn.to[Degree].value === 360.0)
//     }

//     "radian value" in {
//       assert(1.0.turn.to[Radian].value === (2 * pi) +- ε)
//     }

//     "arcminute value" in {
//       assert(1.0.turn.to[Arcminute].value === 21600.0)
//     }

//     "arcsecond value" in {
//       assert(1.0.turn.to[Arcsecond].value === 1296000.0 +- ε)
//     }
//   }

//   "turnsPerSecond" should {
//     "show" in {
//       assert(2.turnsPerSecond.show === "2 tr s^-1 [∠ T^-1]")
//     }

//     "gradiansPerSecond value" in {
//       assert(1.0.turnsPerSecond.to[Gradian].value === 400.0)
//     }

//     "degreesPerSecond value" in {
//       assert(1.0.turnsPerSecond.to[Degree].value === 360.0)
//     }

//     "radiansPerSecond value" in {
//       assert(1.0.turnsPerSecond.to[Radian].value === (2 * pi) +- ε)
//     }

//     "arcminutesPerSecond value" in {
//       assert(1.0.turnsPerSecond.to[Arcminute].value === 21600.0)
//     }

//     "arcsecondsPerSecond value" in {
//       assert(1.0.turnsPerSecond.to[Arcsecond].value === 1296000.0 +- ε)
//     }

//   }
// }

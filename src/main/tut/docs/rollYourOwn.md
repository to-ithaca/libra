---
layout: docs
title:  Roll Your Own
position: 3
---

## Rolling your own

Let's say we want to code up a game of [Catan](http://www.catan.com/game/catan).  SI units don't cut the mustard, so let's make our own.  Our base dimensions are going to be `Stone`, `Wood`, `Wheat`, `Sheep` and `Time`.

```tut:book
import libra._, libra.ops.base._

object catan {
  type Stone
  type Wood
  type Wheat
  type Sheep
  type Time
  
  type Rock = Unit[Stone]
  type Log = Unit[Wood]
  type Bushel = Unit[Wheat]
  type Flock = Unit[Sheep]
  type Turn = Unit[Time]
  
  
  //we ought to provide shows
  implicit def stoneShow: Show[Stone] = Show[Stone]("S")
  implicit def woodShow: Show[Wood] = Show[Wood]("W")
  implicit def wheatShow: Show[Wheat] = Show[Wheat]("F")
  implicit def sheepShow: Show[Sheep] = Show[Sheep]("B")
  implicit def timeShow: Show[Time] = Show[Time]("T")
  
  implicit def rockShow: Show[Rock] = Show[Rock]("Rock")
  implicit def logShow: Show[Log] = Show[Log]("Log")
  implicit def bushelShow: Show[Bushel] = Show[Bushel]("Bushel")
  implicit def flockShow: Show[Flock] = Show[Flock]("Flock")
  implicit def turnShow: Show[Turn] = Show[Turn]("Turn")
  
  
  //implicit conversions would be useful too
  implicit final class CatanOps[A](val a: A) extends AnyVal {
     def logs: QuantityOf[A, Wood, Log] = Quantity(a)
     def rocks: QuantityOf[A, Stone, Rock] = Quantity(a)
     def flocks: QuantityOf[A, Sheep, Flock] = Quantity(a)
     def turns: QuantityOf[A, Time, Turn] = Quantity(a)
  }
}

import spire.implicits._
import catan._

val tradingRate = 4.0.logs / 1.0.flocks
val flocks = 8.0.flocks * tradingRate
flocks.show
```

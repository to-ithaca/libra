---
layout: home
---

Libra is a dimensional analysis library based on [shapeless](https://github.com/milessabin/shapeless), [spire](https://github.com/non/spire) and [singleton-ops](https://github.com/fthomas/singleton-ops).  It contains out of the box support for SI units for all numeric types.

## To Use

Add this to your `build.sbt`:

```scala
resolvers += Resolver.bintrayRepo("to-ithaca", "maven")

libraryDependencies += "com.ithaca" %% "libra" % "0.1.0"
```

## TLDR

Example usage:

```tut:nofail
import spire.implicits._
import libra._, libra.si._
(3.m + 2.m).show
(3.m * 2.m).show
(1.0.km + 2.0.m + 3.0.mm).show
(3.0.s / 3.0.ms).show
//this should fail
3.m + 2.kg
```

## Why?
When we deal with numeric quantities, we often resort to `Int`, `Double` or `Float` types.
These are incommunicative and error prone.

```tut
val distance = 3.0 // 3 m
val time = 2.0 // 2 s
val speed = distance + time // Oh no!
```

There's a mistake in our formula, but we won't know without a decent set of tests.

Libra provides a `Quantity` which wraps base numeric types.  It supports **compile time dimensional analysis**.

```tut:nofail
import spire.implicits._
import libra._, libra.si._
val distance = 3.0.m
val time = 2.0.s
val speed = distance + time
val speed = distance / time // Yay!
speed.show
```

## Operations

### Addition

Here is how you add quantities:

```tut
val q = 3.m + 2.m
q.show
```

You can add quantities for any kind of number type, provided there's an `AdditiveSemigroup` for them.
Here we're using spire's rationals:

```tut
val q = r"1/3".m + r"2/3".m
q.show
```

But you can only add quantities of the same dimension:

```tut:nofail
val q = 3.m + 4.kg
q.show
```

Subtraction is just the same:

```tut
val q = 3.m - 2.m
q.show
```

### Multiplication
Here's how you multiply quantities:

```tut
val q = 3.m * 2.kg
q.show
```
You can also divide quantities:

```tut
val q = 3.0.m / 2.0.kg
q.show
```

### Power
Here's how you raise quantities to a power:

```tut
val q = 3.0.m^(3)
q.show
```

### Inverse
You can also invert quantities:

```tut
val q = 3.0.m.invert
q.show
```

### Extracting the base value
Here's how you extract the value as a numeric in base units:

```tut
3.km.value
```

## Rolling your own

Let's say we want to code up a game of [Catan](http://www.catan.com/game/catan).  The base SI units don't cut the mustard, so let's make our own.  Our base dimensions are going to be `Stone`, `Wood`, `Wheat`, `Sheep` and `Time`.

```tut:nofail
import libra._, libra.ops.base._

object catan {
  type Stone
  type Wood
  type Wheat
  type Sheep
  type Time
  
  //we ought to provide shows
  implicit def stoneShowDimension: ShowDimension[Stone] = ShowDimension[Stone]("S")
  implicit def woodShowDimension: ShowDimension[Wood] = ShowDimension[Wood]("W")
  implicit def wheatShowDimension: ShowDimension[Wheat] = ShowDimension[Wheat]("F")
  implicit def sheepShowDimension: ShowDimension[Sheep] = ShowDimension[Sheep]("B")
  implicit def timeShowDimension: ShowDimension[Time] = ShowDimension[Time]("T")
  
  implicit def stoneShowUnit: ShowUnit[Stone] = ShowUnit[Stone]("Rocks")
  implicit def woodShowUnit: ShowUnit[Wood] = ShowUnit[Wood]("Logs")
  implicit def wheatShowUnit: ShowUnit[Wheat] = ShowUnit[Wheat]("Bushel")
  implicit def sheepShowUnit: ShowUnit[Sheep] = ShowUnit[Sheep]("Sheep")
  implicit def timeShowUnit: ShowUnit[Time] = ShowUnit[Time]("Turn")
  
  
  //implicit conversions would be useful too
  implicit final class CatanOps[A](val a: A) extends AnyVal {
     def wood: OneOf[A, Wood] = Quantity(a)
     def stone: OneOf[A, Stone] = Quantity(a)
     def sheep: OneOf[A, Sheep] = Quantity(a)
     def turns: OneOf[A, Time] = Quantity(a)
  }
}

import spire.implicits._
import catan._

val tradingRate = 4.0.wood / 1.0.sheep
val sheeple = 8.0.wood / tradingRate
sheeple.show
```

## Credits

Libra makes heavy use of [shapeless](https://github.com/milessabin/shapeless), [spire](https://github.com/non/spire) and [singleton-ops](https://github.com/fthomas/singleton-ops).  It wouldn't be possible without these projects, their authors and contributors, so if you like Libra, please check them out.

Libra also uses:

 - [sbt-microsites](https://github.com/47deg/sbt-microsites)
 - [sbt-doctest](https://github.com/tkawachi/sbt-doctest)
 - [scalatest](https://github.com/scalatest/scalatest)

## Alternatives

If Libra isn't quite your cup of tea, definitely take a look at [Squants](https://github.com/typelevel/squants).  It's also a great library for dimensional analysis, with a slightly different scope.

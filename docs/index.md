---
layout: home
---

Libra is a dimensional analysis library based on [shapeless](https://github.com/milessabin/shapeless), [spire](https://github.com/non/spire) and [singleton-ops](https://github.com/fthomas/singleton-ops).  It contains out of the box support for SI units for all numeric types.

## To Use

Libra supports JDK 8, scala versions 2.11.12, 2.12.11 and 2.13.2.

Add this to your `build.sbt`:

```scala
libraryDependencies += "com.github.to-ithaca" %% "libra" % "0.6.0"
```

## TL;DR

Example usage:

```tut:nofail:book
import spire.implicits._
import libra._, libra.implicits._
(3.m + 2.m).show
(3.m * 2.m).show
(1.0.km.to[Metre] + 2.0.m + 3.0.mm.to[Metre]).show
(3.0.s.to[Millisecond] / 3.0.ms).show
3.m + 2.kg // This should fail
```

## Why?
When we deal with numeric quantities, we often resort to `Int`, `Double` or `Float` types.
These are incommunicative and error prone.

```tut:book
val distance = 3.0 // 3 m
val time = 2.0 // 2 s
val speed = distance + time // Oh no!
```

There's a mistake in our formula, but we won't know without a decent set of tests.

Libra provides a `Quantity` which wraps base numeric types.  It supports **compile time dimensional analysis**.

```tut:nofail:book
import spire.implicits._
import libra._, libra.implicits._
val distance = 3.0.m
val time = 2.0.s
distance + time
(distance / time).show // Yay!
```

## Credits

In its incubation, Libra made heavy use of [Typelevel Scala](https://github.com/typelevel/scala).  It still makes use of [shapeless](https://github.com/milessabin/shapeless), [spire](https://github.com/non/spire) and [singleton-ops](https://github.com/fthomas/singleton-ops).  It wouldn't be possible without these projects, their authors and contributors, so if you like Libra, please check them out.

Libra also uses:

 - [sbt-microsites](https://github.com/47deg/sbt-microsites)
 - [sbt-doctest](https://github.com/tkawachi/sbt-doctest)
 - [scalatest](https://github.com/scalatest/scalatest)

## Alternatives

If Libra isn't quite your cup of tea, definitely take a look at [Squants](https://github.com/typelevel/squants).  It's also a great library for dimensional analysis, with a slightly different scope.

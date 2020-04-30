---
layout: docs
title:  Conversions
position: 2
---

Libra has the concept of base *dimensions* and *units*.

A dimension is measured in a unit.
A dimension, such as `Length` is measured in the `SI` unit of `Metre`.  It could also be measured in units of `Centimetre` or even `Kilometre`.

You can convert between quantities of different units, provided that they have the same base dimension.

### Basic conversion
Here's how you convert quantities:

```scala mdoc
import spire.implicits._
import libra._, libra.implicits._

3.0.km.to[Metre].show
```

### Composing conversions

You can compose conversions together:

```scala mdoc
(3.0.km / 1.0.s).to[Centimetre].to[Millisecond].show
```

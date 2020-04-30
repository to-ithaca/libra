---
layout: docs
title:  Operations
position: 1
---

## Operations

Be sure to import spire's implicits

```scala mdoc
import shapeless.syntax.singleton._
import spire.implicits._
import libra._, libra.implicits._
```

### Addition

Here is how you add quantities:

```scala mdoc
(3.m + 2.m).show
```

You can add quantities for any kind of number type, provided there's an `AdditiveSemigroup` for them.
Here we're using spire's rationals:

```scala mdoc
(r"1/3".m + r"2/3".m).show
```

But you can only add quantities of the same dimension:

```scala mdoc:fail
3.m + 4.kg
```

Subtraction is just the same:

```scala mdoc
(3.m - 2.m).show
```

### Multiplication
Here's how you multiply quantities:

```scala mdoc
(3.m * 2.kg).show
```
You can also divide quantities:

```scala mdoc
(3.0.m / 2.0.kg).show
```

You can use euclidean division for discrete numbers:

```scala mdoc
(3.m /~ 2.kg).show
```

### Power
Here's how you raise quantities to a power:

```scala mdoc
(3.0.m^(3.narrow)).show
```

### Inverse
You can also invert quantities:

```scala mdoc
3.0.m.invert.show
```

### Scalar multiplication
You can multiply by scalar values:

```scala mdoc
(3.m :* 3).show
(3 *: 3.m).show
```

### Extracting the base value
Here's how you extract the value as a numeric in base units:

```scala mdoc
3.km.value
```

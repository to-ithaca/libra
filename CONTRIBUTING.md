# Contributing

## How do I help?

As an open source project, Libra gladly welcomes contributions.  We're really happy that you're keen on helping!

## Raise an issue

If you have found a bug, or have an idea for something new, please don't hesitate to [raise an issue](https://github.com/to-ithaca/libra/issues/new).
We'll discuss your idea on the issue, and someone else might decide to implement it.  

## Submit a pull request
Libra uses the [fork and pull](https://help.github.com/articles/about-pull-requests/) model for contributions.

### Claim an issue

If there is already a GitHub issue for the task you are working on, please leave a comment on the issue to let us know that you're working on it.

### Build the project

First you'll need to checkout a local copy of the code base:

```sh
git clone git@github.com:to-ithaca/libra.git
```

Libra is build using [sbt](http://www.scala-sbt.org/).  Run `sbt`, and then `compile` to compile the code or `test` to run all tests.

### Testing

- We use [scalatest](https://github.com/scalatest/scalatest) for our tests
- We use [doctest](https://github.com/tkawachi/sbt-doctest) for scaladoc tests
- Coverage stats are published to [codecov.io](https://codecov.io/gh/to-ithaca/libra)

Travis will run tests with every pull request, but running them locally with `sbt test` might save you some time.

### Make a pull request

Once you've got some code you want to contribute, you can [make a pull request](https://github.com/to-ithaca/libra/pulls/new).
The project maintainers will review it, might suggest changes, and will merge it in if they're happy.

### Grant of licence

Libra falls under the [Apache Licence 2.0](https://github.com/to-ithaca/libra/blob/master/LICENSE).  Opening a pull request signifies that you consent to this licence.

## Code of Conduct

Libra is fully supportive of the [Typelevel Code of Conduct](http://typelevel.org/conduct.html).  We ask that all our contributors adhere to this to create a welcoming envoronment.  If you feel like you're being harrassed, please contact one of [us](https://github.com/to-ithaca/libra#project-maintainers) immediately so that we can support you.

## Credits

This document is heavily based on the [http4s contributor guide](https://github.com/http4s/http4s/blob/master/CONTRIBUTING.md).

## Contributors

The following people have helped made Libra great:

- [Łukasz Drygała](https://github.com/ldrygala)


lazy val buildSettings = Seq(
  organization := "com.ithaca",
  scalaOrganization := "org.typelevel",
  scalaVersion := "2.12.1",
  name         := "libra",
  version      := "0.1.0-SNAPSHOT"
)

lazy val commonScalacOptions = Seq(
  "-encoding", "UTF-8",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:experimental.macros",
  "-language:postfixOps",
  "-Ypartial-unification",
  "-Yliteral-types"
)

lazy val commonResolvers = Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.bintrayRepo("fthomas", "maven")
)

lazy val commonSettings = Seq(
    resolvers := commonResolvers,
    scalacOptions ++= commonScalacOptions,
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.2",
      "eu.timepit" %% "singleton-ops" % "0.0.3",
      "org.spire-math" %% "spire" % "0.13.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
) ++ buildSettings

lazy val root = (project in file("."))
  .settings(commonSettings)

import xerial.sbt.Sonatype.autoImport.sonatypeProfileName

lazy val buildSettings = Seq(
  organization := "com.github.zainab-ali",
  scalaOrganization := "org.typelevel",
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://to-ithaca.github.io/libra/")),
  scalaVersion := "2.12.1",
  crossScalaVersions := Seq("2.11.8", "2.12.1"),
  name         := "libra"
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

lazy val docsMappingsAPIDir = settingKey[String]("Name of subdirectory in site target directory for api docs")



lazy val siteSettings = Seq(
  micrositeName := "Libra",
  micrositeDescription := "Dimensional analysis for Scala",
  micrositeAuthor := "Zainab Ali",
  micrositeHighlightTheme := "ocean",
  micrositeBaseUrl := "libra",
  micrositeGithubOwner := "to-ithaca",
  micrositeGithubRepo := "libra",
  micrositeDocumentationUrl := "api",
  docsMappingsAPIDir := "api",
  addMappingsToSiteDir(mappings in (Compile, packageDoc), docsMappingsAPIDir),
  git.remoteRepo := "git@github.com:to-ithaca/libra.git",
  autoAPIMappings := true
)

lazy val commonSettings = Seq(
    resolvers ++= commonResolvers,
    scalacOptions ++= commonScalacOptions,
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.2",
      "eu.timepit" %% "singleton-ops" % "0.0.3",
      "org.spire-math" %% "spire" % "0.13.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    ),
  doctestTestFramework := DoctestTestFramework.ScalaTest
) ++ buildSettings


lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(siteSettings)
  .enablePlugins(MicrositesPlugin)

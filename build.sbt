import xerial.sbt.Sonatype.autoImport.sonatypeProfileName
import ReleaseTransformations._

lazy val buildSettings = Seq(
  organization := "com.github.to-ithaca",
  scalaOrganization := "org.typelevel",
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://to-ithaca.github.io/libra/")),
  crossScalaVersions := "2.12.1" :: "2.11.8" :: Nil,
  scalaVersion := crossScalaVersions.value.head,
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
      "eu.timepit" %% "singleton-ops" % "0.0.4",
      "org.typelevel" %% "spire" % "0.14.1",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    ),
  doctestTestFramework := DoctestTestFramework.ScalaTest
) ++ buildSettings

val publishSettings = Seq(
  releaseCrossBuild := true,
  releaseIgnoreUntrackedFiles := true,
  sonatypeProfileName := "com.github.to-ithaca",
  developers += Developer("zainab-ali", "Zainab Ali", "", url("http://github.com/zainab-ali")),
  licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("http://to-ithaca.github.io/libra/")),
  scmInfo := Some(ScmInfo(url("https://github.com/to-ithaca/libra"),
    "git@github.com:to-ithaca/libra.git")),
  credentials ++= (for {
    username <- Option(System.getenv().get("SONATYPE_USERNAME"))
    password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
  } yield Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password)).toSeq,
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
    setNextVersion,
    commitNextVersion,
    ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
    pushChanges)
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(publishSettings)
  .settings(siteSettings)
  .enablePlugins(MicrositesPlugin)

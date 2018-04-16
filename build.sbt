import xerial.sbt.Sonatype.autoImport.sonatypeProfileName
import ReleaseTransformations._

import sbt.sbtpgp.Compat.publishSignedConfigurationTask
import com.typesafe.sbt.pgp.PgpKeys._

lazy val buildSettings = inThisBuild(
  Seq(
    scalaOrganization := "org.typelevel",
    scalaVersion := "2.12.4-bin-typelevel-4"
  )) ++ Seq(
  organization := "com.github.to-ithaca",
  licenses += ("Apache-2.0", url(
    "https://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://to-ithaca.github.io/libra/")),
  crossScalaVersions := "2.12.4-bin-typelevel-4" :: "2.11.11-bin-typelevel-4" :: Nil,
  name := "libra"
)

lazy val commonScalacOptions = Seq(
  "-encoding",
  "UTF-8",
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

lazy val docsMappingsAPIDir = settingKey[String](
  "Name of subdirectory in site target directory for api docs")

lazy val siteSettings = Seq(
  micrositeName := "Libra",
  micrositeDescription := "Dimensional analysis for Scala",
  micrositeAuthor := "Zainab Ali",
  micrositeHighlightTheme := "atom-one-light",
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
    scalaOrganization.value % "scala-reflect" % scalaVersion.value % "provided",
    "com.chuusai" %% "shapeless" % "2.3.3",
    "eu.timepit" %% "singleton-ops" % "0.2.2",
    "org.typelevel" %% "spire" % "0.14.1",
    "org.typelevel" %% "spire-laws" % "0.14.1" % "test",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test"
  ),
  doctestTestFramework := DoctestTestFramework.ScalaTest
) ++ buildSettings

val publishSettings = Seq(
  publishTo in ThisBuild := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  publishSignedConfiguration := {
    val prev = publishSignedConfigurationTask.value
    if (isSnapshot.value) {
      prev.withOverwrite(true)
    } else prev
  },
  releaseCrossBuild := true,
  releaseIgnoreUntrackedFiles := true,
  sonatypeProfileName := "com.github.to-ithaca",
  developers += Developer("zainab-ali",
                          "Zainab Ali",
                          "",
                          url("http://github.com/zainab-ali")),
  licenses := Seq(
    "Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("http://to-ithaca.github.io/libra/")),
  scmInfo := Some(
    ScmInfo(url("https://github.com/to-ithaca/libra"),
            "git@github.com:to-ithaca/libra.git")),
  credentials ++= (for {
    username <- Option(System.getenv().get("SONATYPE_USERNAME"))
    password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
  } yield
    Credentials("Sonatype Nexus Repository Manager",
                "oss.sonatype.org",
                username,
                password)).toSeq,
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    ReleaseStep(action = releaseStepCommand("publishSigned"),
                enableCrossBuild = true),
    setNextVersion,
    commitNextVersion,
    ReleaseStep(action = releaseStepCommand("sonatypeReleaseAll"),
                enableCrossBuild = true),
    pushChanges
  )
)

lazy val mimaSettings = Seq(
  mimaPreviousArtifacts := Set("com.github.to-ithaca" %% "libra" % "0.4.0")
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(publishSettings)
  .settings(siteSettings)
  .settings(mimaSettings)
  .enablePlugins(MicrositesPlugin)

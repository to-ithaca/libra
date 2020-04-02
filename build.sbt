import ReleaseTransformations._

lazy val buildSettings = inThisBuild(
  Seq(
    organization := "com.github.to-ithaca",
    homepage := Some(url("https://to-ithaca.github.io/libra/")),
    licenses += ("Apache-2.0", url(
      "https://www.apache.org/licenses/LICENSE-2.0.html")),
    developers += Developer("zainab-ali",
                            "Zainab Ali",
                            "",
                            url("http://github.com/zainab-ali")),
    scmInfo := Some(
      ScmInfo(url("https://github.com/to-ithaca/libra"),
              "git@github.com:to-ithaca/libra.git")),
    scalaVersion := "2.13.0",
    resolvers := Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.bintrayRepo("fthomas", "maven")
    ),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:experimental.macros",
      "-language:postfixOps"
    ),
  )
)

val releaseSettings = Seq(
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  releaseCrossBuild := true,
  releaseIgnoreUntrackedFiles := true,
  sonatypeProfileName := "com.github.to-ithaca",
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
    releaseStepCommandAndRemaining("+test"),
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommandAndRemaining("+publishSigned"),
    setNextVersion,
    commitNextVersion,
    releaseStepCommandAndRemaining("+sonatypeReleaseAll"),
    pushChanges,
    releaseStepCommand("++2.12.8 docs/publishMicrosite")
  )
)

// scalacOptions += "-Ypartial-unification"
lazy val coreSettings = Seq(
  crossScalaVersions := scalaVersion.value :: "2.12.8" :: "2.11.11" :: Nil,
  libraryDependencies ++= Seq(
    scalaOrganization.value % "scala-reflect" % scalaVersion.value % "provided",
    "com.chuusai" %% "shapeless" % "2.3.3",
    "eu.timepit" %% "singleton-ops" % "0.4.4",
    "org.typelevel" %% "spire" % "0.17.0-M1",
    "org.typelevel" %% "spire-laws" % "0.17.0-M1" % "test",
    "org.scalatest" %% "scalatest" % "3.1.1" % "test"
  ),
  doctestTestFramework := DoctestTestFramework.ScalaTest,
  mimaPreviousArtifacts := Set("com.github.to-ithaca" %% "libra" % "0.6.0")
)

lazy val core = (project in file("core"))
  .settings(name := "libra")
  .settings(coreSettings)

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

lazy val docsSettings = Seq(
  publishArtifact := false,
  // sbt-microsites depends on mdoc, which hasn't been published for 2.13.0 yet
  // @see https://github.com/scalameta/mdoc/issues/156
  // We use tut to compile sources, but if we use 2.13.0 sbt tries to pull in
  // mdoc anyway
  scalaVersion := "2.12.8",
  crossScalaVersions := "2.12.8" :: "2.11.11" :: Nil,
)

lazy val docs = (project in file("docs"))
  .settings(name := "docs")
  .settings(siteSettings)
  .settings(docsSettings)
  .enablePlugins(MicrositesPlugin)
  .dependsOn(core)

lazy val rootSettings = Seq(
  crossScalaVersions := Nil,
)

lazy val root = project
  .in(file("."))
  .settings(buildSettings)
  .settings(releaseSettings)
  .settings(rootSettings)
  .aggregate(core, docs)

addCommandAlias("ci", "; project core ; clean ; compile ; coverage ; test ; coverageReport")
addCommandAlias(
  "ciMicrosite",
  "; clean ; compile ; coverage ; test ; coverageReport ; makeMicrosite")

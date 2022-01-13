import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

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
    scalaVersion := "2.13.8",
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

lazy val coreSettings = Seq(
  crossScalaVersions := scalaVersion.value :: "2.12.11" :: Nil,
  libraryDependencies ++= Seq(
    scalaOrganization.value % "scala-reflect" % scalaVersion.value % "provided",
    "com.chuusai" %%% "shapeless" % "2.3.3",
    "eu.timepit" %%% "singleton-ops" % "0.5.2",
    "org.typelevel" %%% "spire" % "0.17.0",
    "org.typelevel" %%% "spire-laws" % "0.17.0" % "test",
    "org.scalatest" %%% "scalatest" % "3.2.2" % "test"
  ),
  doctestTestFramework := DoctestTestFramework.ScalaTest,
  mimaPreviousArtifacts := previousStableVersion.value
    .map(organization.value %% moduleName.value % _).toSet
)

lazy val jsSettings = Seq(
  doctestGenTests := Seq.empty
)

lazy val core =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure)
    .in(file("core"))
    .settings(name := "libra")
    .settings(coreSettings)
    .settings(jsSettings)

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
)

lazy val docs = (project in file("docs"))
  .settings(name := "docs")
  .settings(siteSettings)
  .settings(docsSettings)
  .enablePlugins(MicrositesPlugin)
  .dependsOn(core.jvm)

lazy val rootSettings = Seq(
  crossScalaVersions := Nil,
)

lazy val root = project
  .in(file("."))
  .settings(buildSettings)
  .settings(rootSettings)
  .aggregate(core.jvm, core.js, docs)

addCommandAlias("feature",
  List(
    "project coreJS",
    "clean",
    "compile",
    "test",
    "project coreJVM",
    "clean",
    "compile",
    "coverage",
    "test",
    "coverageReport"
  ).mkString(" ; ")
)
addCommandAlias(
  "featureMicrosite",
  List(
    "clean",
    "compile",
    "makeMicrosite"
  ).mkString(" ; ")
)

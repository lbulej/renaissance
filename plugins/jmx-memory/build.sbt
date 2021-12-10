lazy val renaissanceCore = RootProject(uri("../../renaissance-core"))

lazy val pluginJMXTimers = (project in file("."))
  .settings(
    name := "plugin-jmxmemory",
    version := "0.0.1",
    crossPaths := false,
    autoScalaLibrary := false,
    organization := "org.renaissance",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    packageOptions := Seq(
      sbt.Package.ManifestAttributes(
        ("Renaissance-Plugin", "org.renaissance.plugins.jmxmemory.Main")
      )
    ),
  )
  .dependsOn(renaissanceCore % "provided")

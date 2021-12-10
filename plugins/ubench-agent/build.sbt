lazy val renaissanceCore = RootProject(uri("../../renaissance-core"))

lazy val ubenchAgent = {
  val github = "git://github.com/D-iii-S/java-ubench-agent.git"
  val commit = "5e8473dbc4d38948dc2d680b5fdf0775824ad40f"
  RootProject(uri(s"${github}#$commit"))
}

lazy val pluginUbenchAgent = (project in file("."))
  .settings(
    name := "plugin-ubenchagent",
    version := "0.0.1",
    crossPaths := false,
    autoScalaLibrary := false,
    organization := (renaissanceCore / organization).value,
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
      case PathList("org", "renaissance", "plugins", _*) => MergeStrategy.first
      case PathList("org", "renaissance", _*) => MergeStrategy.discard
      case _ => MergeStrategy.singleOrError
    },
    Compile / unmanagedSourceDirectories += (ubenchAgent / baseDirectory).value / "src" / "java",
    packageOptions := Seq(
      sbt.Package.ManifestAttributes(
        ("Renaissance-Plugin", "org.renaissance.plugins.ubenchagent.Main")
      )
    ),
  )
  .dependsOn(renaissanceCore % "provided", ubenchAgent)

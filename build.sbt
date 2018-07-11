lazy val root = project
  .in(file("."))
  .settings(
    name := "rock-paper-scissor",
    scalaVersion := "2.12.3",
    resolvers += "buildo at bintray" at "https://dl.bintray.com/buildo/maven",
    libraryDependencies += "io.buildo" %% "enumero" % "1.2.0",
    addCompilerPlugin(
      "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
  )

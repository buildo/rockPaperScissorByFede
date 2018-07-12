val circeVersion = "0.9.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "rock-paper-scissor",
    scalaVersion := "2.12.3",
    resolvers += "buildo at bintray" at "https://dl.bintray.com/buildo/maven",
    resolvers += Resolver.bintrayRepo("hseeberger", "maven"),
    libraryDependencies += "io.buildo" %% "enumero" % "1.2.0",
    libraryDependencies += "io.buildo" %% "enumero-circe-support" % "1.3.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.13",
    libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.3",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.13",
    libraryDependencies += "de.heikoseeberger" %% "akka-http-circe" % "1.21.0",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),
    addCompilerPlugin(
      "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
  )

scalaVersion := "2.12.12"

lazy val http4sVersion = "0.21.8"

libraryDependencies ++= Seq(
  "org.http4s"   %% "http4s-core"      % http4sVersion,
  "org.http4s"   %% "http4s-dsl"       % http4sVersion,
  "org.http4s"   %% "http4s-server"    % http4sVersion,
  "org.http4s"   %% "http4s-blaze-server" % http4sVersion
)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")

fork := true
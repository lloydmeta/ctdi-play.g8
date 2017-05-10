name := """$name$"""

version := "$version$"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "$scala_version$"

libraryDependencies ++= Seq(
  "com.softwaremill.macwire" %% "macros" % "$macwire_version$" % Provided,
  "org.scalatestplus.play" %% "scalatestplus-play" % "$scalatestplay_version$" % Test
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint:-unused,_",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused:-imports,_",
  "-Xfuture"
)

wartremoverErrors in (Compile, compile) ++= Warts.unsafe
wartremoverExcluded ++= routes.in(Compile).value
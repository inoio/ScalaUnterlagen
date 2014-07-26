//
// Proxy configuration:
// set e.g. http_proxy=http://username:password@hostname:port;  export http_proxy
// in your .profile file
//

name := "Training"

organization := "oose"

version := "0.0.1"

scalaVersion := "2.11.1"

val akkaVersion = "2.3.3"

val scalazVersion = "7.1.0-RC1"

val specs2Version = "2.3.13-scalaz-"+scalazVersion

libraryDependencies ++= Seq (
	"org.scalaz" %% "scalaz-core" % scalazVersion,
        // only for testing (scope test)
        "org.specs2" %% "specs2" % specs2Version % "test",
        "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
        "org.scalaz" %% "scalaz-effect" % scalazVersion,
        // https://github.com/scala/async
        // will be introduced in scala 2.11.x
        "org.scala-lang.modules" %% "scala-async" % "0.9.1",
        // dispatch - async http Library
        // "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
        "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-agent" % akkaVersion,
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion %  "test",
        "com.chuusai" %% "shapeless" % "2.0.0" 
)

//addCompilerPlugin("org.brianmckenna" %% "wartremover" % "0.10")

//scalacOptions in (Compile, compile) ++= Seq("-P:wartremover:only-warn,wartremover:traverser:org.brianmckenna.wartremover.warts.Unsafe")

scalacOptions ++= Seq("-Xlint", "-unchecked", "-deprecation", "-feature")

// keep only specifications ending with Spec or Unit
testOptions := Seq(Tests.Filter(s => Seq("Spec", "Test").exists(s.endsWith(_))))

// configure the typesafe console for akka monitoring
// see project/plugins.sbt
// currently only works for the Akka 2.2.x series, not Akka 2.3.x
// atmosSettings

// ScalaStyle
org.scalastyle.sbt.ScalastylePlugin.Settings

// definition of kind - thanks to http://eed3si9n.com/learning-scalaz/Kinds.html
// 
initialCommands in console := """
  import scalaz._
  import Scalaz._
"""


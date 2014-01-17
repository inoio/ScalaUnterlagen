name := "Training"

organization := "oose"

version := "0.0.1"

val akkaVersion = "2.2.3"

val scalazVersion = "7.0.5"

libraryDependencies ++= Seq (
	"org.scalaz" %% "scalaz-core" % scalazVersion,
        // only for testing (scope test)
        "org.specs2" %% "specs2" % "2.3.7" % "test",
        "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
        "org.scalaz" %% "scalaz-effect" % scalazVersion,
        "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-agent" % akkaVersion,
        "com.chuusai" % "shapeless" % "2.0.0-M1" cross CrossVersion.full
)

// keep only specifications ending with Spec or Unit
testOptions := Seq(Tests.Filter(s => Seq("Spec", "Unit").exists(s.endsWith(_))))

// configure the typesafe console for akka monitoring
// see project/plugins.sbt
atmosSettings

// this would be an example to set other default src/test directories
	
// scalaSource in Compile := baseDirectory.value / "src"

//scalaSource in Test := baseDirectory.value / "test"

initialCommands in console := """
  import scalaz._
  import Scalaz._
"""


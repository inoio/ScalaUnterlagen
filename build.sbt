name := "Training"

organization := "oose"

version := "0.0.1"

libraryDependencies ++= Seq (
	"org.scalaz" %% "scalaz-core" % "7.0.5",
        // only for testing (scope test)
        "org.specs2" %% "specs2" % "2.3.7" % "test",
        "org.scalaz" %% "scalaz-concurrent" % "7.0.5",
        "org.scalaz" %% "scalaz-effect" % "7.0.5",
        "org.scalaz" %% "scalaz-typelevel" % "7.0.5",
        "com.typesafe.akka" %% "akka-actor" % "2.2.1",
        "com.typesafe.akka" %% "akka-agent" % "2.2.1",
        "org.specs2" %% "specs2" % "2.2.2" % "test",
        "com.chuusai" % "shapeless" % "2.0.0-M1" cross CrossVersion.full
)

// keep only specifications ending with Spec or Unit
testOptions := Seq(Tests.Filter(s => Seq("Spec", "Unit").exists(s.endsWith(_))))

// this would be an example to set other default src/test directories
	
// scalaSource in Compile := baseDirectory.value / "src"

//scalaSource in Test := baseDirectory.value / "test"

initialCommands in console := """
  import scalaz._
  import Scalaz._
"""


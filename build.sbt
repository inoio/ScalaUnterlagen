name := "Training"

organization := "oose"

version := "0.0.1"

scalaVersion := "2.10.3"

val akkaVersion = "2.2.3"

val scalazVersion = "7.1.0-M6"

val specs2Version = "2.3.10-scalaz-"+scalazVersion

libraryDependencies ++= Seq (
	"org.scalaz" %% "scalaz-core" % scalazVersion,
        // only for testing (scope test)
        "org.specs2" %% "specs2" % specs2Version % "test",
        "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
        "org.scalaz" %% "scalaz-effect" % scalazVersion,
        // https://github.com/scala/async
        // will be introduced in scala 2.11.x
        "org.scala-lang.modules" %% "scala-async" % "0.9.0-M4",
        // dispatch - async http Library
        // "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
        "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-agent" % akkaVersion,
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion %  "test",
        "com.chuusai" % "shapeless" % "2.0.0-M1" cross CrossVersion.full
)

//addCompilerPlugin("org.brianmckenna" %% "wartremover" % "0.8")

//scalacOptions in (Compile, compile) ++= Seq("-P:wartremover:only-warn,wartremover:traverser:org.brianmckenna.wartremover.warts.Unsafe")

scalacOptions ++= Seq("-Xlint", "-unchecked", "-deprecation", "-feature")

// keep only specifications ending with Spec or Unit
testOptions := Seq(Tests.Filter(s => Seq("Spec", "Test").exists(s.endsWith(_))))

// configure the typesafe console for akka monitoring
// see project/plugins.sbt
atmosSettings

// ScalaStyle
org.scalastyle.sbt.ScalastylePlugin.Settings

// definition of kind - thanks to http://eed3si9n.com/learning-scalaz/Kinds.html
// 
initialCommands in console := """
  import scalaz._
  import Scalaz._
  def kind[A: scala.reflect.runtime.universe.TypeTag]: String = {
  import scala.reflect.runtime.universe._
  def typeKind(sig: Type): String = sig match {
    case PolyType(params, resultType) =>
      (params map { p =>
        typeKind(p.typeSignature) match {
          case "*" => "*"
          case s   => "(" + s + ")"
        }
      }).mkString(" -> ") + " -> *"
    case _ => "*"
  }
  def typeSig(tpe: Type): Type = tpe match {
    case SingleType(pre, sym) => sym.companionSymbol.typeSignature
    case ExistentialType(q, TypeRef(pre, sym, args)) => sym.typeSignature
    case TypeRef(pre, sym, args) => sym.typeSignature
  }
  val sig = typeSig(typeOf[A])
  val s = typeKind(sig)
  sig.typeSymbol.name + "'s kind is " + s + ". " + (s match {
    case "*" =>
      "This is a proper type."
    case x if !(x contains "(") =>
      "This is a type constructor: a 1st-order-kinded type."
    case x =>
      "This is a type constructor that takes type constructor(s): a higher-kinded type."
  })
  }
"""


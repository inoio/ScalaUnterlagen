package demoeither

import scala.util._
import scalaz._
import Scalaz._

object Main extends App {

  case class Version(major: Int, minor: Int)
  object Version {
    private def validate(nr: Int, name: String): Either[String, Int] = {
      if (nr >= 0) Right(nr) else Left(s"$name muss >= 0 sein.")
    }
    def validate(major: Int, minor: Int): Either[NonEmptyList[String], Version] = {
      // does not compile
      //    		for {
      //    		  resultminor <- validate(minor)
      //    		  resultmajor <- validate(major)
      //    		} yield {
      //    		  ???
      //    		}

      // returns an Either[String, Version] but only when everything works
      // else first error
      // see RightProjection.flatMap
      for {
        minor <- validate(minor, "minor").right
        major <- validate(major, "major").right
      } yield { Version(major, minor) }
 
      // accumulating errors  ?
      val majorVal : ValidationNel[String, Int] = Validation.fromEither(validate(major, "major")).toValidationNel
      val minorVal : ValidationNel[String, Int] = Validation.fromEither(validate(minor, "minor")).toValidationNel
      
      val res = (majorVal |@| minorVal) { case (x,y) => Version(x,y)}

      val applicative = (validate(major, "major") |@| validate(minor, "minor")) { case (major, minor) => Version(major, minor) }
      applicative
      
      res.toEither
      
    }
  }

  println(Version.validate(-1, -1))
  println(Version.validate(0, -1))
  println(Version.validate(1, -1))
  println(Version.validate(1, 1))
}

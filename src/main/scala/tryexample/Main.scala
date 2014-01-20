package tryexample

object Main extends App {

  import java.io._
  import scala.io._
  import scala.util.{ Try, Success, Failure }
  def openStream(name: String): Try[InputStream] = {
    Try {
      getClass().getClassLoader().getResourceAsStream(name)
    }
  }

  def readStream(is: InputStream): Try[(Source, String)] = {
    Try {
      val source = scala.io.Source.fromInputStream(is)
      val result = source.getLines().mkString("\n")
      (source, result)
    }
  }

  def closeStream(source: Source): Try[Unit] = {
    Try {
      source.close
    }
  }

  val result = for {
    stream <- openStream("application2.conf")
    (source, result) <- readStream(stream)
    _ <- Try { println(result) }
  } yield { closeStream(source) }

  result match {
    case Success(cs) => println("Success")
    case Failure(ex) =>
      println("Failed:")
  }

}
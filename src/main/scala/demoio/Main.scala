package demoio

object Main extends App {
  
  import scalaz._
  import Scalaz._
  import scalaz.effect.IO
  import scalaz.syntax.monad._
  
  /**
   * Einfache Anwendung der IO Monade in einer for comprehension.
   */
  def greeting() = {
    for {
      _ <- IO { println("Enter your name") }
      name <- IO { readLine() } except{ case e: Exception => IO("D'oh!") }
      result <- IO { println(s"Welcome $name")
    	  			name
      			}  
    } yield result
  }
  
  println("Start...")
  val x = greeting()
  val z = greeting()
  val y = greeting()
  val name = x.unsafePerformIO()
  println(s"End: $name")
}
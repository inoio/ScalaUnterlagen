package demoio

import java.io.InputStream
import scalaz.effect.Resource
import scalaz.effect.IO
import java.io.FileInputStream

object ResourceDemon extends App {
  
  implicit def inputstream2Resource(is: InputStream) = new Resource[InputStream] {
    def close(is: InputStream) = IO { 
      is.close()
    }
  }
  
  val is : InputStream = new FileInputStream("./test.dat")
  
  val something = IO { println ("hello")}.flatMap( x => IO[Unit] {println("world")})
   
  something.unsafePerformIO

}
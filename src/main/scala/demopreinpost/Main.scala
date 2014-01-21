package demopreinpost

object Main extends App {
  import Implicits._
  val i: Int = 3

  // präfix
  // eingeschränkt auf +,-,~ und !
  println(!i)
  
  // infix
  println(i.postFix(3))
  println(i postFix 3 * 2)
  println(i postFix 3 + 2)
  
  // postfix
  println(20 !) 
}

object Implicits {
  implicit class MyInt(val self: Int) extends AnyVal {
    def unary_!() = if (self == 0) false else true

    def postFix(some: Int) = self + some

    def !(): Int = if (self == 0) 1 else {
      val x = self - 1
      self * (x.!)
    }
  }
}




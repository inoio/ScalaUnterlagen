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
  println(20!)
}






package demopattern

object Main extends App {
  
  val x : Option[Int] = Some(1)
  
  x match {
    case _ => println("Matched immer / wildcard")
  }
  
  x match {
    case identifier => println(s"Matched immer $identifier")
  }
  
  x match {
    case typ : Option[Int] => println(s"Matched immer gegen den Typ Option[Int] $typ")
  }
  
  x match {
    case Some(identifier) => println(s"extrahiert: $identifier")
    case _ => println("matcht nicht")
  }
  
  x match {
    case None => println(s"x ist nicht None")
    case _ => println("match weil nicht none")
  }

}
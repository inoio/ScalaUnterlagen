package demospecialfunctions

object Main extends App {

  val pers = Person("Klink", "Markus")
  val Person(n, vn) = pers

  println(s"Name $n")
  println(s"Vorname $vn")
  val pers2: Person = null
  pers2 match {
    case Person(xn, xvn) => println(s"$xn , $xvn")
    case _ => println("null")
  }
  
  val x = Person.unapply(pers2)
  println(x)
  
  val -->(n1, v1)  = Person("Klink", "Markus")
  println(s"$n1 $v1")
  val name --> vorname = Person("Klink", "Markus")
  println(s"$name $vorname")
}

class Person(val name: String, val vorname: String)
object Person {
  def apply(name: String, vorname: String) = new Person(name, vorname)
  def unapply(p: Person) = if (p eq null) None else Some((p.name, p.vorname))
}

object --> {
  def unapply(p: Person) = Person.unapply(p)
}
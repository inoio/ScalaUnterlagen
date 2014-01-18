package demoio

case class User(uuid: Option[String] = None, name: String, vorname: String, alter: Int)

object User {
  def apply(n: String, v: String, a: Int) : User = 
    User(name = n, vorname = v, alter = a)
}
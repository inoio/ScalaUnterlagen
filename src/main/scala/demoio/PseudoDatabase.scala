package demoio

import scalaz._
import Scalaz._
import scalaz.effect._
import scalaz.effect.IO._

object TestPseudoDatabase extends App {

  val db = PseudoDatabase
 
  def save(u: User): IO[User] = db.write(u)
  def read(uuid: String): IO[User] = db.read(uuid)
  def delete(u: User): IO[Unit] = db.delete(u)

  val user = User("Klink", "Markus", 41)

  val action = save(user).flatMap(user => read(user.uuid.get))
  println("RESULT: " + action.unsafePerformIO)

} 

case class User(uuid: Option[String] = None, name: String, vorname: String, alter: Int)

object User {
  def apply(n: String, v: String, a: Int) : User = 
    User(name = n, vorname = v, alter = a)
}
object PseudoDatabase {
  
  private[this] var users : Map[String, User] = Map.empty
  
  def read(uuid: String) : IO[User] = IO {
    println(s"Reading $uuid")
    users(uuid)
  }
  
  def write(user : User) = IO {
    println(s"writing $user")
    val newUuid : String =      
    		user.uuid.getOrElse(java.util.UUID.randomUUID.toString)
    val newUser = user.copy(uuid = Some(newUuid))
    println(s"assigned $newUuid")
    users = users +  (newUuid -> newUser)
    println(s"db size: ${users.size}")
    newUser
  }
  
  def delete(user: User) = IO {
    println(s"deleting $user")
    users = users - user.uuid.get
  }

}
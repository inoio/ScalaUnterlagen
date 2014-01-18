package demoio

import scalaz._
import Scalaz._
import scalaz.effect._
import scalaz.effect.IO._

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
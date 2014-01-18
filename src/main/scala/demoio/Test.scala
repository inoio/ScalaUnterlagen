package demoio

import scalaz._
import Scalaz._
import scalaz.effect._

object TestPseudoDatabase extends App {

  val db = PseudoDatabase

  def save(u: User): IO[User] = db.write(u)
  def read(uuid: String): IO[User] = db.read(uuid)
  def delete(u: User): IO[Unit] = db.delete(u)

  val user = User("Klink", "Markus", 41)

  val action = save(user).flatMap(user => read(user.uuid.get))
  println("RESULT: " + action.unsafePerformIO)

}
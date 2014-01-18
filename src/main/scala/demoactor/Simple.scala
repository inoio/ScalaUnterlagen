package demoactor

import akka.actor._
import akka.pattern._
import akka.util._
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits._

object Simple extends App {
  
  val actorSystem = ActorSystem("MySystem")
  val actor = actorSystem.actorOf(Props[Responder])
  implicit val timeout : Timeout = 5.seconds
  
  actor ! SetName("Markus")
  actor ! SetName("Johannes")
  
  val msg = Await.result(
      (actor ? Hello)
      .mapTo[Reply], 5.seconds)
  println(msg.reply)

}

class Responder extends Actor {
  var name : Option[String] = None
  
  def receive = {
    case SetName(n) => name = Some(n)
    case Hello => {
      val answer = name getOrElse("I haven't got a name yet")
      sender ! Reply(answer)
    }
  }
}

case class SetName(name : String)
case object Hello
case class Reply(reply: String)
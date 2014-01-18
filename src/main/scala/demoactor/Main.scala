package demoactor

import akka.actor._
import scala.concurrent.duration._

import akka.event.LoggingReceive

object Main extends App {

  val actorSystem = ActorSystem("system")
  val actor1 = actorSystem.actorOf(Props[AnActor], "actor1")
  val actor2 = actorSystem.actorOf(Props[AnActor], "actor2")
  val inbox = Inbox.create(actorSystem)

  inbox.send(actor1, StartMsg(actor2))

  println("Enter something to stop the actor system.")
  Console.readLine()
  println(s"received in Inbox ${inbox.receive(5 seconds)}")
  actorSystem.shutdown()
  actorSystem.awaitTermination()
}

class AnActor extends Actor {

  implicit val ec = context.dispatcher

  var anotherActor: ActorRef = _

  def receive = LoggingReceive {
    case StartMsg(startSender) =>
      anotherActor = startSender
      startSender ! Ping
      sender ! "Ok, ok, ok - I start."
    case Ping =>
      sender ! Pong
    case Pong =>
      sender ! StopMsg
    case StopMsg =>
      context.stop(self)

  }
}

case object Ping
case class StartMsg(anotherActor: ActorRef)
case object StopMsg
case object Pong

package akkaexample

import akka.actor._

case class Monitor(node: ActorRef) extends Actor {

  import context._
  import protocol._

  var originalSender: Option[ActorRef] = None

  def receive = {
    case IsUpRequest =>
      originalSender = Some(sender)
      node ! Ping
    case Pong =>
      originalSender.map(s => s ! Up)
  }
}

case class NetworkNode extends Actor {

  import context._
  import protocol._

  def receive = {
    case Ping => sender ! Pong
  }
}

object protocol {
  case object Ping
  case object Pong

  case object IsUpRequest
  case object Up
  case object Down
}
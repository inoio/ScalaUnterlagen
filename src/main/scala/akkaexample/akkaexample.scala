package akkaexample

import akka.actor._



case class Monitor extends Actor {
  
  import context._
  
  val node = actorOf(Props[NetworkNode], "node")
  
  def receive = Actor.emptyBehavior
  
}

case class NetworkNode extends Actor {
  
  import context._
  
  def receive = Actor.emptyBehavior
}

object protocol {
  case object Ping
  case object Pong
  
  case object IsUpRequest
  case object Up
  case object Down
}
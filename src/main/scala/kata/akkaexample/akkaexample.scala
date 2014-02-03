package kata.akkaexample

import akka.actor._
import akka.pattern._
import protocol._
import akka.event._
import scala.concurrent._
import scala.concurrent.duration._
import scala.util._
import akka.actor.SupervisorStrategy._
import scala.language.postfixOps

class Monitor(netWorkNode: ActorRef) extends Actor with ActorLogging {

  import context._
  import collection._

  var counter = 0

  private case object TimerTick

  implicit val timeout: akka.util.Timeout = 750 milliseconds

  val timer = system.scheduler.scheduleOnce(1 second, self, TimerTick)

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute, loggingEnabled = true) {
    case _: ArithmeticException => Resume
    case _: NullPointerException => Restart
    case _: IllegalArgumentException => Stop
    case _: Exception => Escalate
  }
  def receive: Receive = LoggingReceive {
    case IsUpRequest =>
      log.info(s"Anfrage von ${sender}")
      actorOf(Props(classOf[SubMonitor], sender))

    case TimerTick =>
      val reply = netWorkNode ? Ping
      reply onComplete {
        case Success(Pong) =>
          val selection = actorSelection(self.path / "*")
          selection ! Up
          system.scheduler.scheduleOnce(1 second, self, TimerTick)
        case Failure(e) =>
          log.info(self.toString())
          val selection = Option(actorSelection(self.path / "*"))
          selection.map(s => s ! Down)
          system.scheduler.scheduleOnce(1 second, self, TimerTick)
        case _ =>
          // log.error("unexpected message")
          system.scheduler.scheduleOnce(1 second, self, TimerTick)
      }

    case Pong =>
      val selection = actorSelection(self.path / "*")
      selection ! Up

  }
}

class SubMonitor(replyTo: ActorRef) extends Actor {

  def receive = {
    case Up =>
      replyTo ! Up
      context.stop(self)
    case Down =>
      replyTo ! Down
      context.stop(self)
  }
}

class NetworkNode extends Actor {

  import context._

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

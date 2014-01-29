package akkaexample

import akka.actor._
import org.specs2.mutable._
import org.specs2.time._
import oose.akka.test._
import akka.testkit._
import scala.concurrent.duration._

class AkkaExampleSpec extends Specification with NoTimeConversions {

  import protocol._
  "The monitor" should {

    "reply with up " in new AkkaSpecs2Scope {
      val nodeActor = system.actorOf(Props[NetworkNode], "node")
      val monitorActor = system.actorOf(Props(classOf[Monitor], nodeActor), "monitor")

      monitorActor ! IsUpRequest
      expectMsg(5 seconds, Up)
    }

    "repeat the test" in new AkkaSpecs2Scope {
      val nodeActor = system.actorOf(Props[NetworkNode], "node")
      val monitorActor = system.actorOf(Props(classOf[Monitor], nodeActor), "monitor")

      monitorActor ! IsUpRequest
      expectMsg(5 seconds, Up)
    }

    "does the monitor actually ping the node" in new AkkaSpecs2Scope {
      val nodeActor = TestProbe()
      val monitorActor = system.actorOf(Props(classOf[Monitor], nodeActor.ref), "monitor")

      monitorActor ! IsUpRequest
      nodeActor.expectMsg(5 seconds, Ping)
    }
  }
}
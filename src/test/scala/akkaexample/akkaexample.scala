package akkaexample

import akka.actor._
import org.specs2.mutable._
import org.specs2.time._
import oose.akka.test._
import akka.testkit._

case class AkkaExampleSpec extends Specification with NoTimeConversions {

  // do not execute tests in parallel
  // makes analyzing mistakes easier
  sequential

  import protocol._
  "The monitor" should {

    "reply with up" in new AkkaSpecs2Scope {
      val nodeActor = system.actorOf(Props[NetworkNode], "node")
      val monitorActor = system.actorOf(Props(classOf[Monitor], nodeActor), "monitor")

      monitorActor ! IsUpRequest
      expectMsg(Up)
    }

    "repeat the test" in new AkkaSpecs2Scope {
      val nodeActor = system.actorOf(Props[NetworkNode], "node")
      val monitorActor = system.actorOf(Props(classOf[Monitor], nodeActor), "monitor")

      monitorActor ! IsUpRequest
      expectMsg(Up)
    }

    "does the monitor actually ping the node" in new AkkaSpecs2Scope {
      val nodeActor = TestProbe()
      val monitorActor = system.actorOf(Props(classOf[Monitor], nodeActor.ref), "monitor")


      monitorActor ! IsUpRequest
      nodeActor.expectMsg(Ping)
    }
  }
}
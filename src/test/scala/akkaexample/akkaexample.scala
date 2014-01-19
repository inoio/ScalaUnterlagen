package akkaexample

import akka.actor._
import org.specs2.mutable._
import org.specs2.time._
import oose.akka.test._


case class AkkaExampleSpec extends Specification with NoTimeConversions {
  
  import protocol._
  "The monitor" should {
    
    "reply with up " in new AkkaSpecs2Scope {
      val monitorActor = system.actorOf(Props[Monitor], "monitor")
      
      
      monitorActor ! IsUpRequest 
      expectMsg(Up)
    }
  }
}
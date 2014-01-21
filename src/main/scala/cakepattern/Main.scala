package cakepattern

object Main extends App with ConcreteCake1Component with Cake2Component {
  val cake2Service = new Cake2Service {}

  println(cake2Service.cake2Function())
}

trait Cake1Component {
  self =>
  val cake1Service: Cake1Service
  trait Cake1Service {
    def cake1Function(): String
  }
}

trait ConcreteCake1Component extends Cake1Component {
  self =>
  val cake1Service = new ConcreteCake1Service

  class ConcreteCake1Service extends Cake1Service {
    def cake1Function(): String = "Hello"
  }
}

trait Cake2Component {
  self: Cake1Component =>

  val cake2Service: Cake2Service
  trait Cake2Service {
    def cake2Function() = {
      cake1Service.cake1Function() + ", world"
    }
  }

}
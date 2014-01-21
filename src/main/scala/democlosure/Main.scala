package democlosure

object Main extends App {

  // Simple
  class Demo(var x: Int) {
    val add: Int => Int = { y: Int => x + y }
    def getX() = x
  }

  // Complex
  class DemoOuter(val some: Int = 0) {
    def demo(x: Int) = new Demo(x)
    class Demo(var x: Int) {
      val add: Int => Int = { y: Int => x + y + some }
      def getX() = x
    }
  }

  val demo = new Demo(3)
  val adder = demo.add
  // prints 7
  println(s"adder(4) = ${adder(4)}")

  println("Set x to 5")
  demo.x = 5
  // prints 9
  println(s"adder(4) = ${adder(4)}")

}
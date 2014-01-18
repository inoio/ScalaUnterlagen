package demoimplicit

object Main extends App {
  import Helper._

  implicit val e = 0.00001
  println("The result is: " + (3.0 =~= 3.0000001))
  println("The result is: " + (3.0 =~= 3.0001))
  println("The result is: " + (3.0.=~=(3.0001)(0.1)))
}

object Helper {

  implicit class DoubleWrapper(val underlying: Double)
    extends AnyVal {
    def =~=(that: Double)(implicit epsilon: Double) = {
      ((underlying - that).abs <= epsilon)
    }
  }
}
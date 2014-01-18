package demoimplicit


object Alternative extends App {
  import RichDouble._
  import Precision._
  implicit val precision = new Precision(0.001)
  implicit val precision2 = 0.000132
  println("The result is : " + (3.0 =~= 3.0000001)) 
  println ("The result is : " + (3.0.=~=(3.0001)(0.1)))
  println ("The result is : " + (3.0.=~=(3.0001)))
}


class RichDouble(val self: Double) extends Proxy {
  def =~=(that: Double)(implicit epsilon : Precision) = {
    math.abs(self - that) <= epsilon.self
  }
}

class Precision(val self: Double) extends Proxy
object Precision {
  implicit def doubleToPrecision(that: Double) : Precision = new Precision(that)
}

object RichDouble {
  implicit def doubleToRichDouble(that: Double) : RichDouble = new RichDouble(that)
}
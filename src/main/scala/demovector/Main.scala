package demovector
import scala.math._
object Main extends App {

  import LowLevelImplicits._

  println(new Vector(0, 0) == new Vector(0, 1))
  println(new Vector(0, 0) == new Vector(0, 0))
  println(new Vector(0, 0) == null)
  println(-(new Vector(1, 2)))

  println(3.0 * new Vector(1.0, 2.0))
}

final class Vector[T: Numeric](val x: T, val y: T) {
  import math.Numeric.Implicits._
  def +(that: Vector[T]) = new Vector(x + that.x, y + that.y)
  def unary_-() = new Vector(-x, -y)
  override def equals(obj: Any) = obj match {
    case v: Vector[T] => v.x == x && v.y == y
    case _ => false
  }
  override def hashCode() = {
    41 * (
      (
        x.hashCode() + 41)
        + y.hashCode())
  }
  override def toString() = s"($x,$y)"
}

object LowLevelImplicits {
  implicit class VectorMult[T](val self: T) extends AnyVal {
    import math.Numeric.Implicits._
    def *(that: Vector[T])(implicit num: Numeric[T]): Vector[T] =
      new Vector(self * that.x, self * that.y)

  }
}
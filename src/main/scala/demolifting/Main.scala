package demolifting

object Main extends App {

  case class Pair[A](a: A, b: A)

  case object Pair {
    /**
     *  liftet eine Funktion A=>B in den "Kontext" Pair
     */
    def lift1[A, B](f: A => B): Pair[A] => Pair[B] = {
      p: Pair[A] =>
        Pair(f(p.a), f(p.a))
    }

    /**
     *  liftet eine Function (A, A) => B in den "Kontext" Pair
     */
    def lift2[A, B](f: (A, A) => B): (Pair[A], Pair[A]) => Pair[B] = {
      (p1: Pair[A], p2: Pair[A]) => Pair(f(p1.a, p2.a), f(p1.b, p2.b))
    }
    
  }

  val x = Pair(3, 2)
  def invertInt(x: Int) = -1 * x
  val invert = Pair.lift1(invertInt)
  println(invert(x))

  val y = Pair(4, 2)
  val add = Pair.lift2 { (x: Int, y: Int) => x + y }

  println(add(x, y))

}
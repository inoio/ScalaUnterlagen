package demotraits

object CacheSimple extends App {

  val g = new Line(3, 0) with Cached

  println(g.getOrFetch(3)(g(3)))
  println(g.getOrFetch(3)(g(3)))

  val h = new Line(3, 0) with Cached {
    override def apply(x: Int) = {
      getOrFetch(x)(super.apply(x))
    }
  }

  println(h(3))
  println(h(3))
}

case class Line(m: Int, y0: Int) {
  def apply(x: Int) = m * x + y0
}

trait Cached {
  private var cache: Map[Int, Int] = Map.empty

  def getOrFetch(x: Int)(f: => Int) = {
    cache.get(x) match {
      case Some(x) =>
        println("cache hit")
        x
      case None =>
        println("cache missed")
        val newX = f
        cache = cache + (x -> newX)
        newX
    }
  }
}
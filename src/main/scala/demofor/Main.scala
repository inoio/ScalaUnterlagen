package demofor

object Main extends App {

  // 1 generator 
  def add1(x: Option[Int]): Option[Int] = {
    val debug = x.map(a => a + 1)
    for {
      a <- x
    } yield a + 1
  }

  // 2 Generatoren
  def add(x: Option[Int], y: Option[Int]): Option[Int] = {
    val debug = x.flatMap(a => for (b <- y) yield a + b)
    val debug2 = x.flatMap(a => y.map(b => a + b))

    for {
      a <- x
      b <- y
    } yield a + b

  }

  // 3 Generatoren
  def add(x: Option[Int], y: Option[Int], z: Option[Int]): Option[Int] = {
    val debug =
      x.flatMap(a => y.flatMap(b => z.map(c => a + b + c)))

    for {
      a <- x
      b <- y
      c <- z
    } yield a + b + c
  }

  println(add(None, None))
  println(add(Some(1), Some(2)))
  println(add(Some(1), None))

}
package typeclasses

// Generator example by Martin Odersky

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

trait Applicative[F[_]] {
  def ap[A, B](fa: F[A])(f: F[A] => F[B]): F[B]
}

trait Monad[F[_]] {
  def flatMap[A, B](fa: F[A])(f: A => F[B])(implicit func: Functor[F]): F[B] =
    flatten(func.map(fa)(f))
  def flatten[A](fa: F[F[A]]): F[A]
}

trait Generator[A] {
  def generate: A
}

case object integer extends Generator[Int] {
  private val rnd = new java.util.Random()
  override def generate: Int = {
    rnd.nextInt
  }
}

object implicits {
  implicit val genFunctor: Functor[Generator] = new Functor[Generator] {
    final override def map[A, B](fa: Generator[A])(f: A => B): Generator[B] = {
      new Generator[B] {
        def generate = f(fa.generate)
      }
    }
  }

  implicit def functorOps[M[_]: Functor, A](ma: M[A]) = new {
    val functor = implicitly[Functor[M]]
    def map[B](f: A => B) = functor.map(ma)(f)
  }

  implicit val genMonad: Monad[Generator] = new Monad[Generator] {
    final override def flatten[A](faa: Generator[Generator[A]]): Generator[A] = faa.generate
  }

  implicit def monadOps[M[_]: Monad: Functor, A](ma: M[A]) = new {
    val monad = implicitly[Monad[M]]
    def flatMap[B](f: A => M[B]) = monad.flatMap(ma)(f)
    def flatten[B](implicit ev: M[A] <:< M[M[B]]): M[B] = monad.flatten(ma)
  }
}

object Test extends App {
  import implicits._
  val bool: Generator[Boolean] = for {
    x <- integer
  } yield { x > 0 }

  def pairs[U, T](u: Generator[U], t: Generator[T]) = for {
    a <- u
    b <- t
  } yield { (a, b) }

  def single[T](x: T) = new Generator[T] {
    def generate = x
  }

  def choose(low: Int, high: Int) = for {
    x <- integer
  } yield { low + x % (high - low) }

  def oneOf[T](elems: T*) = for {
    x <- choose(0, elems.length)
  } yield { elems(x) }

  def randomList : Generator[List[Int]] = {
    def emptyList = single(Nil)
    def nonEmptyList = {
      for {
        head <- integer
        tail <- randomList
      } yield { head :: tail }
    }
    
    for {
      empty <- bool
      list <- if (empty) emptyList else nonEmptyList
    } yield {list }
  }
 
  def test[T](numTimes: Int, g: Generator[T])(f: T => Boolean) :Unit = {
    for (i <- 0 until numTimes) {
      val value = g.generate
      assert(f(value), s"Test failes for $value")
    }
    println("Test passed")
  }
  
  test(100, pairs(randomList, randomList)) {
    case (xs,ys) => (xs ++ ys).length > ys.length
  }
  
  println(bool.generate)
  println(bool.generate)
  println(bool.generate)
  println(bool.generate)
}
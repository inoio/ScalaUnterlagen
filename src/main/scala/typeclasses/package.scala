package object typeclasses {

  implicit def functorOps[F[_]: Functor, A](ma: F[A]) = new {
    private val functor = implicitly[Functor[F]]
    final def map[B](f: A => B): F[B] = functor.map(ma)(f)
  }
  
  implicit def monadOps[F[_] : Monad, A](ma: F[A]) = new {
    private val monad = implicitly[Monad[F]]
    final def flatten[B](implicit ev : F[A] <:< F[F[A]]) = monad.flatten(ma)    
  }
  
  implicit def applicativeOps[F[_] : Applicative, A](ma: F[A]) = new {
    private val applicative = implicitly[Applicative[F]]
    final def ap[B](f: F[A] => F[B]) : F[B] = applicative.ap(ma)(f)
  }

}
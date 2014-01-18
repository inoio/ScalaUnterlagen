package demorandom

object ws {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  case class Future[A](val x : A) {
  
  	def map[B](f: A=>B) : Future[B] =
  		Future(f(x))
  		
    def flatMap[B](f: A=>Future[B]) : Future[B] =
    	f(x)
  }
  
  val z = Future(3)                               //> z  : demorandom.ws.Future[Int] = Future(3)
  val t = Future(4)                               //> t  : demorandom.ws.Future[Int] = Future(4)
  val x = for {
  		g <- z
  		h <- t
  } yield g*h                                     //> x  : demorandom.ws.Future[Int] = Future(12)
}
package demorandom

object Main  extends App{
  
  trait Generator[+T] {
    self => 
    def generate : T
    def map[S](f: T => S) : Generator[S] = new Generator[S] {
      def generate = f(self.generate)
    }
    
    def flatMap[S](f: T => Generator[S]) : Generator[S] = new Generator[S] {
      def generate = {
        f(self.generate).generate
      }
    }
  }
  
  val integer = new Generator[Int]  {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }
  
  val bool = for (x <- integer) yield x > 0
  
  for (_ <- 1 to  10) {
    println(bool.generate)
  }
  
  
}
package demoparcollections

object Main extends App {

  val numbers  = (BigInt(1) to 2000)

  def faculty(i: BigInt): BigInt = {
    @scala.annotation.tailrec
    def faculty(total: BigInt, n: BigInt): BigInt = {
      if (n == 0) total else faculty(total * n, n - 1)
    }
    faculty(1, i)
  }

  val nonParColl = numbers.map(i =>  faculty(i))
  
  val parColl = ???
  
  // assert(parColl.diff(nonParColl).isEmpty)

}
package demoparcollections

object Main extends App {

  val numbers  = (BigInt(1) to 2000)

  assert(faculty(3) == 6)

  def faculty(i: BigInt): BigInt = {
    @scala.annotation.tailrec
    def faculty(total: BigInt, n: BigInt): BigInt = {
      if (n == 0) total else faculty(total * n, n - 1)
    }
    faculty(1, i)
  }

  val nonParColl = utiltimer.stopwatch({
    numbers.map(faculty(_))
  }, "sequential execution")

  // keine Seiteneffekte
  // Operationen die reduce, die auf mehrere Elemente zugreifen, müssen assoziate
  // Funktionen aufrufen, e.g. (a+b) == (b + a), aber (a-b) != (b-a)
  // lohnt nur bei grossen Collections (size > 1000nde)
  
  val parColl = utiltimer.stopwatch ({
    numbers.par.map(faculty(_))
  }, "parallel execution  ").seq
  
  assert(parColl.diff(nonParColl).isEmpty)

}
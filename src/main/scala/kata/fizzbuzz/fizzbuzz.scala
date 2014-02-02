package kata.fizzbuzz

trait Fizzbuzz {
  def fizzbuzz(i: Int) : String = ""
}

trait Exercise {
  sef: Fizzbuzz =>
    override def fizzbuzz(i: Int) = {
      (i % 3 == 0, i % 5 == 0) match {
        case (true, true) => "fizzbuzz"
        case (true, false) => "fizz"
        case (false, true) => "buzz"
        case _ => i.toString()
      }
    }
}
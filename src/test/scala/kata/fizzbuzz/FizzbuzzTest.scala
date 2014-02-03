package kata.fizzbuzz

import org.specs2.mutable._
import org.specs2.ScalaCheck
import org.scalacheck.Prop
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.scalacheck.Gen
import org.specs2.matcher.Parameters
@RunWith(classOf[JUnitRunner])
class FizzbuzzTest extends Specification with ScalaCheck {

  val solution = new Fizzbuzz with Exercise {}

  // Number generators
  val smallInteger = Gen.choose(0, 1000)
  val by3 = for {
    i <- smallInteger
  } yield i * 3
  val by5 = for {
    i <- smallInteger
  } yield i * 5
  val by3and5 = for {
    i <- smallInteger

  } yield i * 3 * 5

  "Fizzbuzz" should {
    "return fizz for multiples of 3" in {
      Prop.forAll(by3) { (n: Int) =>
        (n % 5 != 0) ==>
          (solution.fizzbuzz(n) must be equalTo ("fizz"))
      }
    }

    "return buzz for multiples of 5" in {
      Prop.forAll(by5) { (n: Int) =>
        (n % 3 != 0) ==>
          (solution.fizzbuzz(n) must be equalTo ("buzz"))
      }
    }
    
    "return fizzbuzz for multiples of 3 and 5" in {
      Prop.forAll(by3and5) { (n: Int) =>
          (solution.fizzbuzz(n) must be equalTo ("fizzbuzz"))
      }
    }
    
    "return i for other values" in {
      Prop.forAll(smallInteger) { (n: Int) =>
        ((n >= 0) && (n % 3 != 0) && (n % 5 != 0)) ==>
          (solution.fizzbuzz(n) must be equalTo (n.toString()))
      }
    }
  }

}
package kata.functions

import org.specs2.mutable._
import org.junit.runner._
import org.specs2.runner._
import org.specs2.ScalaCheck

@RunWith(classOf[JUnitRunner])
class FunctionsTest extends Specification with ScalaCheck {
 
  val solution = new Exercise {}

  "Exercise soll " >> {
    "richtige Werte für alle geraden liefern" in {
      prop { (x: Double, m: Double, y0: Double) =>
        (solution.gerade(m, y0)(x) must beEqualTo(m * x + y0)) }
    }
    "foo richtig berechnen" in {
      prop {(x: Double, m: Double, y0: Double) => 
        (solution.foo)(m)(y0)(x) must beEqualTo(solution.gerade(m, y0)(x))}
    }
    
    "bar richtig berechnen" in {
      prop {(x: Double, m: Double, y0: Double) => 
        val foo = solution.foo
        (solution.bar(foo)(m, y0, x) must beEqualTo(solution.gerade(m, y0)(x)))}
    }
  }
}
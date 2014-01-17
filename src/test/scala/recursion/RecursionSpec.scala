package recursion

import org.specs2.mutable._
import org.junit.runner._
import org.specs2.runner._
import org.specs2.ScalaCheck
import org.scalacheck.Prop
@RunWith(classOf[JUnitRunner])
class RecursionSpec extends Specification with ScalaCheck {

  val solution = new RecursiveReverse {}

  "Die rekursive Implementierung sollte" >> {

    "für alle Werte die richtige umgekehrte Reihenfolge ergeben" in {
      prop { liste: List[Int] => solution.reverse(liste) must beEqualTo(liste.reverse) }
    }

    "für alle Werte die richtigen 'takes' ermitteln" in {
      Prop.forAll { (n: Int, liste: List[Int]) => n > 0 ==> (solution.take(liste, n) must beEqualTo(liste.take(n))) }
    }
  }

}
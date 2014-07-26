package kata.recursion

import org.specs2.mutable._
import org.junit.runner._
import org.specs2.runner._
import org.specs2.ScalaCheck
import org.scalacheck.Prop

@RunWith(classOf[JUnitRunner])
class RecursionTest extends Specification with ScalaCheck {

  def cp(input : List[List[Int]])  : List[List[Int]] = {
     println(s"input $input")
     input match {
       case Nil => 
         println("Match nil")
         List(List())
       case xs :: xss => for {
         x <- xs
         ys <- cp(xss)
       } yield {  x :: ys }
     }
   }
  
  val solution = new RecursiveReverse {}

  "Die rekursive Implementierung sollte" >> {

    "für alle Werte die richtige umgekehrte Reihenfolge ergeben" in {
      prop { liste: List[Int] => solution.reverse(liste) must beEqualTo(liste.reverse) }
    }

    "für alle Werte die richtigen 'takes' ermitteln" in {
      Prop.forAll { (n: Int, liste: List[Int]) => n > 0 ==> (solution.take(liste, n) must beEqualTo(liste.take(n))) }
    }
    
    "für 2 Listen die richtige Addition durchführen" in {
      prop {(l1 : List[Int], l2 : List[Int]) => solution.add(l1, l2) must beEqualTo(l1 ++ l2)}
    }
    
    "das cartesiche Produkt wird richtig berehnet" in {
      prop {(l1 : List[List[Int]]) => solution.cp(l1) must beEqualTo(cp(l1)) }
    }
  }

}
  
  
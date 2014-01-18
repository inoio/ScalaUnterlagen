package recursion

trait RecursiveReverse {

  /**
   * Implementiere diese Funktion
   * so dass gilt:
   * reverse(List(1,2,3,4)) == List(4,3,2,1))
   *
   * Verwende dabei nicht die eingebaute Listenfunktion reverse,
   * sondern baue eine rekursive Lösung
   */
  def reverse(l: List[Int]): List[Int] = {
    l match {
      case Nil => Nil
      case head :: tail => reverse(tail) :+ head
    }
  }

  /**
   * Implementiere eine rekursive Funktion, die von einer gegebenen Liste
   * n Elemente vorne entfernt, und diese Werte zurückliefert.
   * take(List(1,2,3,4,5), 3 ) = List(1,2,3)
   * Möchte man mehr Elemente nehmen als die Liste hat, wird die größtmögliche Menge zurückgeben.
   * start sbt mit : ~testOnly recursion.RecursionSpec
   */
  def take(l: List[Int], n: Int): List[Int] = {
    def internal(l: List[Int], result: List[Int], n: Int): List[Int] = {
      if (n > 0) {
        l match {
          case Nil => result
          case head :: tail => internal(tail, result :+ head, n - 1)
        }
      } else { result }
    }
    internal(l, Nil, n)
  }

  /**
   * Implementiere eine rekursive Funktion, die zwei Listen miteinander verknüpft,
   * so dass gilt: List(1,2,3) + List(4,5,6) = List(1,2,3,4,5,6)
   */
  def add(l1: List[Int], l2: List[Int]): List[Int] = {
    l2 match {
      case Nil => l1
      case head :: tail => add(l1 :+ head, tail)
    }
  }

}
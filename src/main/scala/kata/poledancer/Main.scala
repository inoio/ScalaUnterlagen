package kata.poledancer

object Main extends App {

  /**
   * Aufgabe:
   * (aus Learn yourself some Haskell fro greater good)
   * 
   * Ein Drahtseilartist mit einer Balancierstange auf der sich rechts und links Vögel
   * niederlassen können, ist so lange in Balance, wie der Unterschied der Vögel links und rechts
   * auf der Stange nicht größer als 3 ist.
   */
  case class PoleDancer(birdsLeft: Int = 0, birdsRight: Int = 0) {
    private def isValid = {
      (birdsLeft >= 0 && birdsRight >= 0 && math.abs(birdsLeft - birdsRight) <= 3)
    }
    def rightBirds(birds: Int): PoleDancer = copy(birdsRight = birdsRight + birds)
    def leftBirds(birds: Int): PoleDancer = copy(birdsLeft = birdsLeft + birds)
  }

  val p1 = PoleDancer()
  val p2 = p1.rightBirds(1)
  val p3 = p2.leftBirds(-2) // hier eigentlich "tot"
  val p4 = p3.leftBirds(3)
  println(s"Schlechtes Ergebnis: $p4")

  /**
   * Implementiere die Klasse PoleDancer neu. Überlege dir eine typsichere API.
   * Wie können Aufrufe von rightBirds / leftBirds miteinander verkettet werden, 
   * so dass es keine Toten und Wiederauferstehungen gibt?
   */

  case class PoleDancer2(birdsLeft: Int = 0, birdsRight: Int = 0) {
    private def isValid(birdsLeft: Int, birdsRight: Int) = {
      (birdsLeft >= 0 && birdsRight >= 0 && math.abs(birdsLeft - birdsRight) <= 3)
    }
    def rightBirds(birds: Int): Option[PoleDancer2] =
      if (isValid(birdsLeft, birdsRight + birds))
        Some(copy(birdsRight = birdsRight + birds))
      else None
    def leftBirds(birds: Int): Option[PoleDancer2] =
      if (isValid(birdsLeft + birds, birdsRight))
        Some(copy(birdsLeft = birdsLeft + birds))
      else None
  }
  
  val p5 = PoleDancer2()
  val result = for {
    x <- p5.rightBirds(1)
    y <- x.leftBirds(-1)
    z <- y.leftBirds(3)
  } yield z
  println(result)
  
}
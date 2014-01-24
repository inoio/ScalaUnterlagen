package oscilloscope

import ui._
import model._

/**
 * Global application object
 */
object Oscilloscope extends DataProviderComponent with DisplayComponent with InputComponent with App {
  val display = new Display {}
  val input = new Input {}
  
  /** 
   *  Quick and dirty test dataprovider with random numbers 
   */
  val dataProvider = new DataProvider {

    private val randomGenerator = new java.util.Random()

    private def randomDataPoint: Option[Boolean] = {
      Some(randomGenerator.nextBoolean())
    }

    def data = {
      val ch1 = randomDataPoint
      val ch2 = randomDataPoint
      val logic = lift2(logicAnd)(ch1, ch2)
      DataPoints(ch1, ch2, logic) 
    }
  }

  while (true == true) {
    println(display.display)
    println(display.display)
    println(display.display)
    input.command
  }
}
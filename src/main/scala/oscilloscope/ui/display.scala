package oscilloscope.ui

import oscilloscope.ui.parser._
import oscilloscope.model._

/**
 * display a datapoint from the DataProviderComponent
 * in nice colors
 */
trait DisplayComponent {
  self: DataProviderComponent =>
  val display: Display

  trait Display {

    val channelColor = Map(0 -> Console.RED, 1 -> Console.BLUE, 2 -> Console.GREEN)
    val disabledColor = Console.CYAN

    /**
     * display three lines for a single datapoint from the dataprovider
     */
    def display: String = {
      val dp = dataProvider.data

      def singleDP(dp: Option[Boolean], channel: Int): String = {
        val color = channelColor.getOrElse(channel, disabledColor)
        val resultString = dp match {
          case Some(true) => "         |    "
          case Some(false) => "    |         "
          case None => "    .         "
        }
        s"${color}${resultString}${Console.RESET}"
      }

      s"""| ${singleDP(dp.ch1, 0)}${singleDP(dp.ch2, 1)}${singleDP(dp.logic, 2)}
          | ${singleDP(dp.ch1, 0)}${singleDP(dp.ch2, 1)}${singleDP(dp.logic, 2)}
          | ${singleDP(dp.ch1, 0)}${singleDP(dp.ch2, 1)}${singleDP(dp.logic, 2)}""".stripMargin
    }

  }
}

trait InputComponent {
  self =>
  val input: Input
  trait Input {

    val parser = new OscilloscopeParser()

    /**
     *  get a command from the command line
     */
    def command: Command = {
      print(" > ")
      val result = scala.io.StdIn.readLine()
      parser(result) match {
        case Some(r) =>
          println(s"Confirmed: $r")
          r
        case _ =>
          println("Huh?")
          command
      }
    }
  }
}

trait DataProviderComponent {
  self =>
  val dataProvider: DataProvider
  trait DataProvider {
    def data: DataPoints

    def logicAnd(ch1: Boolean, ch2: Boolean) = ch1 && ch2

    def logicOr(ch1: Boolean, ch2: Boolean) = ch1 || ch2

    /**
     *  Lift an operation (Boolean, Boolean) => Boolean
     *  into an operation of
     *  (Option[Boolean], Option[Boolean] => Option[Boolean]
     */
    def lift2(op: (Boolean, Boolean) => Boolean): (Option[Boolean], Option[Boolean]) => Option[Boolean] = {
      (op1: Option[Boolean], op2: Option[Boolean]) =>
        for {
          a <- op1
          b <- op2
        } yield op(a, b)
    }
  }
}



package oscilloscope
package ui

package parser {

  import scala.util.parsing.combinator.JavaTokenParsers

  class OscilloscopeParser extends JavaTokenParsers {

    def channel: Parser[Int] = wholeNumber ^^ { wholeNum => wholeNum.toInt }

    def enabledisablechannelTerm: Parser[Command] = ("enable" | "disable") ~ channel ^^ {
      case "enable" ~ c => Enable(c)
      case "disable" ~ c => Disable(c)
    }

    def enabledisableallTerm: Parser[Command] = ("enable" | "disable") ~ "all" ^^ {
      case "enable" ~ _ => EnableAll
      case "disable" ~ _ => DisableAll
    }

    def modeTerm: Parser[Command] = "mode" ~ ("and" | "or") ^^ {
      case "mode" ~ "or" => ModeOR
      case "mode" ~ "and" => ModeAND
    }

    def expr: Parser[Command] = enabledisableallTerm | enabledisablechannelTerm | modeTerm

    def apply(input: String): Option[Command] = parseAll(expr, input) match {
      case Success(result, _) => Some(result)
      case failure: NoSuccess => None
    }
  }
}
package oscilloscope.ui
package parser

import org.specs2.mutable._

class OscilloscopeParserSpec extends Specification {

  val parser = new OscilloscopeParser()

  "The parser " should {
    "parse enable 9" in {
      parser("enable 9") must beSome(Enable(9))
    }
    "parse disable 34" in {
      parser("disable 34") must beSome(Disable(34))
    }
    "parse disable all" in {
      parser("disable all") must beSome(DisableAll)
    }
    "parse enable all" in {
      parser("disable all") must beSome(DisableAll)
    }
    "parse mode or" in {
      parser("mode or") must beSome(ModeOR)
    }
    "parse mode and" in {
      parser("mode and") must beSome(ModeAND)
    }
    "not parse ena 3" in {
      parser("ena 3") must beNone
    }
  }
}
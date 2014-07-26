package kata.sudoku

import org.scalacheck.Arbitrary
import org.scalacheck.Gen.choose
import org.scalacheck.Gen.listOfN
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

import kata.sudoku.types.Row
import types.Digit
import types.Grid
import types.Row

class SudokuTest extends Specification with ScalaCheck {

  import types._

  implicit def arbDigit: Arbitrary[Digit] = Arbitrary { choose('0', '9') }

  implicit def arbRow: Arbitrary[Row[Digit]] = Arbitrary { listOfN(9, arbDigit.arbitrary) }

  implicit def arbGrid: Arbitrary[Grid] = Arbitrary { listOfN(9, arbRow.arbitrary) }

  "TestData Grids" should {
    "have 9 lines" in {
      check { (grid: Grid) =>
        grid must haveSize(9)
      }
    }
    
    "have 9 colums" in {
      check { (row: Row[Digit]) => row must haveSize(9) }
    }
    
    "have right values in the field" in {
      check { (digit : Digit) => digit must beOneOf(('0' to '9') : _*)}
    }
  }

}
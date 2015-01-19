package kata.sudoku

import org.scalacheck.{Prop, Arbitrary}
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
  

  val solution = new Exercise {}
  import solution._

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

  "Grid" should {
    "have a correct method to extract rows" in {
      check { (grid : Grid) =>  (rows compose rows)(grid) must beEqualTo(grid)}
    }

    "have a correct method to transpose a grid" in {
      check { (grid : Grid) =>  (columns[Digit] _ compose columns[Digit] _)(grid) must beEqualTo(grid)}
    }

    "have a correct method to group /ungroup a grid" in {
      check { (grid : Grid) =>  ( (ungroup[Digit] _ compose group[Digit] _)(grid.flatten) must beEqualTo(grid.flatten))}
    }
    "have a correct method to group /ungroup a grid" in {
      check { (grid : Grid) =>  ( (ungroup[Digit] _ compose group[Digit] _)(grid.flatten) must beEqualTo(grid.flatten))}
    }
    "have a correct group method 2" in {
      (arbRow) { (list : List[Digit]) => list.grouped(3).toList must beEqualTo(solution.group(list))}
    }
    
    "have a correct group boxs method" in {
     check { (grid : Grid) => ((boxs[Digit] _) compose (boxs[Digit] _))(grid) must beEqualTo(grid)}
    }
  }
//  List(7, 1, 8, 0, 9, 6, 9, 4, 9, 2, 0, 0, 1, 3, 2, 2, 9, 5, 4, 6, 2, 9, 9, 0, 1, 6, 8, 3, 8, 3, 4, 4, 7, 7, 7, 5, 5, 8, 0, 2, 1, 1, 3, 0, 4, 4, 1, 5, 3, 4, 9, 6, 7, 7, 3, 8, 0, 6, 6, 3, 1, 0, 5, 1, 6, 3, 9, 0, 4, 9, 4, 6, 9, 5, 0, 9, 2, 5, 0, 8, 1)
//  List(7, 1, 8, 0, 9, 6, 9, 4, 9, 2, 0, 0, 1, 3, 2, 2, 9, 5, 4, 6, 2, 9, 9, 0, 1, 6, 8, 3, 8, 3, 4, 4, 7, 7, 7, 5, 5, 8, 0, 2, 1, 1, 3, 0, 4, 4, 1, 5, 3, 4, 9, 6, 7, 7, 3, 8, 0, 6, 6, 3, 1, 0, 5, 1, 6, 3, 9, 0, 4, 9, 4, 6, 9, 5, 0, 9, 2, 5, 0, 8, 1)
}
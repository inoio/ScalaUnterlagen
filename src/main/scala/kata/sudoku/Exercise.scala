package kata.sudoku
import scala.language.postfixOps

object types {
  type Matrix[A] = List[Row[A]]
  type Row[A] = List[A]

  type Grid = Matrix[Digit]
  type Digit = Char

  type Choices = List[Digit]
}
trait Exercise {

  import types._

  val digits = ('1' to '9').toList

  val choices: Grid => Matrix[Choices] = { grid =>
    val choice: Digit => Choices = { digit =>
      digit match {
        case '0'      => digits
        case a: Digit => List(a)
      }
    }
    grid.map(_.map(choice))
  }

  val expand: Matrix[Choices] => List[Grid] = { matrix =>
    def cp[A](input: List[List[A]]): List[List[A]] = {
      input match {
        case Nil => List(List.empty)
        case xs :: xss => for {
          x <- xs
          ys <- cp(xss)
        } yield { x :: ys }
      }
    }

    cp(matrix).map(cp)
  }

  /**
   * split a list into a list of 3.
   */
  def group[A](list: List[A]): List[List[A]] = {
    list match {
      case Nil => Nil
      case _   => (list take 3) :: group(list drop 3)
    }
  }

  def ungroup[A](ll : List[List[A]]) : List[A] = ll.flatten

  def boxs[A](m : Matrix[A]) : Matrix[A] =  {
    (((a: Matrix[A])             => a.map(z => group(z))) andThen
    ((x: List[List[List[A]]])       => group(x)) andThen
    ((x: List[List[List[List[A]]]]) => x.map(z => columns(z))) andThen
    ((x: List[Matrix[List[A]]]) => ungroup(x)) andThen
    ((x: List[Row[List[A]]])       => x.map(z => ungroup(z))))(m)
  }


  val rows: Grid => List[Row[Digit]] = { matrix => matrix }

  def columns[A](grid: Matrix[A]): Matrix[A] = {
    grid match {
      case Nil => Nil
      case xs :: Nil => for {
        x <- xs
      } yield List(x)
      case xs :: xss =>
        (xs, columns(xss)).zipped map (_ :: _)
    }
  }


  val valid: Grid => Boolean = {
    grid =>
      false
  }

  val filterGrid: List[Grid] => List[Grid] = _.filter(valid)

  val solve: Grid => List[Grid] = {
    (filterGrid compose expand compose choices)
  }


}

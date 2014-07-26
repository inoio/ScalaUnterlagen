package kata.sudoku
import scala.language.postfixOps
trait Exercise {

 type Matrix[A] = List[Row[A]]
 type Row[A] = List[A]

 type Grid = Matrix[Digit]
 type Digit = Char
 
 type Choices = List[Digit]
 
 val digits = ('1' to '9').toList
 
 val choices : Grid => Matrix[Choices] = { grid => 
   val choice : Digit => Choices = { digit =>
     digit match {
       case '0' => digits
       case a : Digit => List(a)
     }
   }
   grid.map(_.map(choice))
 }

 
 val expand : Matrix[Choices] => List[Grid] = { matrix => 
   def cp[A](input : List[List[A]])  : List[List[A]] = {
     input match {
       case Nil => List(List.empty)
       case xs :: xss => for {
         x <- xs
         ys <- cp(xss)
       } yield {  x :: ys }
     }
   }

   cp(matrix).map(cp)
 }
 
 val valid : Grid => Boolean = ???
 
 val filterGrid : List[Grid] => List[Grid] = _.filter(valid)
 
 val  solve : Grid => List[Grid] = {
    (filterGrid compose expand compose choices)
 }
 
 

}

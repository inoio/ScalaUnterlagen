package demoshapeless
import shapeless._
import poly._

// choose is a function from Sets to Options with no type specific cases
object choose extends (Set ~> Option) {
  def apply[T](s : Set[T]) = s.headOption
}

package demopreinpost

import scala.annotation.tailrec

object Implicits {
  implicit class MyInt(val self: Int) extends AnyVal {
    def unary_!() = if (self == 0) false else true

    def postFix(some: Int) = self + some

    def !(): Int = if (self == 0) 1 else {
      val x = self - 1
      self * (x.!)
    }
  }
}
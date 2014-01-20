package demo.pack

// wildcard
import scala.collection._
// scala.collection is already in scope
import mutable._
// alias
import java.util.{Random => RandomGenerator}
// multiple single imports
import java.util.{List, Vector, Set}


class ScopeDemo {}

package demo {
  package pack {

    class ScopeDemo2 {
      import java.io._
      val x = new ScopeDemo()
      val io = new FileOutputStream("test.dat")
      val y = {
        import sub._
        import ScopeDemo2.foo
        new ScopeDemo4()
        foo()
      }
    }
    
    object ScopeDemo2 {
      def foo() = "foo"
    }
  }
}

package demo.pack.sub {
  class ScopeDemo3 {
    val x = new ScopeDemo
  }
  class ScopeDemo4
}


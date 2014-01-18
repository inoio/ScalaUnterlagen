package demo.pack

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


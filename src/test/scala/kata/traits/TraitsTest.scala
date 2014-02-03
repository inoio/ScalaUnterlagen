package kata.traits

import org.specs2.mutable._
import org.junit.runner._
import org.specs2.runner._
import org.specs2.ScalaCheck
@RunWith(classOf[JUnitRunner])
class TraitsTest extends Specification with ScalaCheck {
 
  case class CacheCheck(a: Int) {
    var hits = 0
    def f() = {
      hits = hits + 1
      a * 3
    }
  }

  "Exercise soll " >> {

    "den richtigen Wert zurückliefern" in {
      val solution = new Cache[CacheCheck] with Exercise[CacheCheck]
      val c = CacheCheck(3)
      val result = solution.getOrFetch(c) { c.f() }
      result must beEqualTo(9)
    }

    "den Cache benutzen" in {
      val solution = new Cache[CacheCheck] with Exercise[CacheCheck]
      val c = CacheCheck(10)
      val result = solution.getOrFetch(c) { c.f() }
      result must beEqualTo(30)
      c.hits must beEqualTo(1)
      val result2 = solution.getOrFetch(c) { c.f() }
      result2 must beEqualTo(30)
      c.hits must beEqualTo(1)
    }

    // TODO besseren Test ausdenken.
    "den StressTest bestehen" in {
      val solution = new Cache[CacheCheck] with Exercise[CacheCheck]
      prop { (x: Int) =>
        solution.getOrFetch(CacheCheck(x))(x * x) must beEqualTo(x * x)
      }
    }
  }
}
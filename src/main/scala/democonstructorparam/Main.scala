package democonstructorparam

object Main extends App {

  /**
   * Demonstriere, wie sich verschiedene Notationen der Konstruktorparameter
   * auf die Namensgebung und Sichtbarkeit auswirken.
   */
  class Foo(x: Int, y: Int) {

    def bar(foo: Foo) = foo

  }

  val foo = new Foo(5, 3)

}

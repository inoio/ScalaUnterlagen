package kata.functions

trait Exercise {

  /**
   * Implementiere eine Gerade  nach der Formel f(x) = m * x + y0
   * @return Double => Double
   */
  def gerade(m: Double, y0: Double): Double => Double = {
    x => m * x + y0
  }

  /**
   * Überführe die Funktion gerade mit einer Zeile in die folgende Gestalt.
   */
  def foo: (Double => (Double => (Double => Double))) = {
    (gerade _).curried
  }
 
  /**
   * Verwandel die Methode f (Übergabeparameter) in die Gestalt
   * (Double, Double, Double => Double
   * also 3 Eingangsparameter und als Rückgabefunktion ein Double.
   * @see scala.Function
   */
  def bar(f: (Double => (Double => (Double => Double)))): (Double, Double, Double) => Double = {
    Function.uncurried(f)
  }
}
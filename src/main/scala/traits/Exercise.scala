package traits

trait Cache[A] {
  /**
   * val myCache = new Cache[Int, Int] with Exercise[Int, Int]
   * val result = myCache.getOrFetch(3) { 6 }
   *
   * @return den Wert B für a aus dem Cache, oder berechne das zugehörige B und stelle es in den Cache.
   */
  def getOrFetch(a: A)(f: => Int): Int = {
    1 // irrelevante Dummy Implementierung
  }

}
/**
 * Implementiere den Trait Cache.
 * 1. so dass getOrFetch überschrieben wird
 * 2. so dass es nicht gültig ist den Trait alleine zu verwenden!
 *    - d.h. es ist ungültig zu schreiben. val myCache = new Exercise...
 *
 *    ~testOnly traits.ExerciseSpec
 */
trait Exercise[A] {
 
}

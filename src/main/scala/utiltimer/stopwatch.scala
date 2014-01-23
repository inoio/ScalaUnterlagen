package utiltimer

object stopwatch {
 def apply[A](f: => A, comment : String = "") = {
   import scala.concurrent.duration._
   val startTime = System.nanoTime()
   val result = f
   val endTime = System.nanoTime()
   val duration = Duration(endTime - startTime, NANOSECONDS)
   val c = if (!comment.isEmpty()) s"$comment : " else ""
   println(s"${c}Time passed: ${duration} nanoseconds / ${duration.toSeconds} seconds")
   result
 }
}

object humanStopwatch {
 def apply[A](f: => A, comment : String = "") = {
   import scala.concurrent.duration._
   val startTime = System.nanoTime()
   val result = f
   val endTime = System.nanoTime()
   val duration = Duration(endTime - startTime, SECONDS)
   val c = if (!comment.isEmpty()) s"$comment : " else ""
   println(s"${c}Time passed: ${duration}  / ${duration.toDays} days")
   result
 }
}

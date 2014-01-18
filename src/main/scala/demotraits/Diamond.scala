package demotraits

// Beispiel für "multiple inheritance" und Algorithmus der Class Linearization
object Diamond extends App {
  new AmphibienFahrzeug().fahren
}

trait Beweglich {
  def fahren = println("pronto")
}

trait WasserFahrzeug extends Beweglich {
  override def fahren = {
    println("Schraube antreiben")
  }
}

trait LandFahrzeug extends Beweglich {
  override def fahren = {
    println("Motor antreiben")
  }
}

class AmphibienFahrzeug extends LandFahrzeug with WasserFahrzeug 

// AmphibienFZ, WasserFZ(L), LandFZ(L)
// AmphibienFZ, WasserFZ, Beweglich, LandFZ(L)
// AmphibienFZ, WaserFZ, Beweglich, LandFZ, Beweglich
// AmphibienFZ, WasserFZ, LandFZ, Beweglich + ScalaObject, AnyRef, Any
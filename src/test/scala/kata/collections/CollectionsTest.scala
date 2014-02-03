package kata.collections

import org.specs2.mutable._
import org.junit.runner._
import org.specs2.runner._

@RunWith(classOf[JUnitRunner])
class CollectionsTest extends SpecificationWithJUnit {

  val sut: Exercise = new Exercise with Data {}

  "System under Test sollte" >> {
    "Kontinentnamen richtig bestimmen" in {

      val solution = sut.alleNamenAllerKontinente
      solution must contain(exactly("Europa", "Afrika"))

    }

    "Ländernamen richtig bestimmen" in {
      val solution = sut.alleLänderNamen
      solution must contain(exactly(
        "Ägypten",
        "Tunesien",
        "Deutschland",
        "Polen",
        "Frankreich"))
    }

    "Städenamen richtig bestimmen" in {
      val solution = sut.alleStädteNamen
      solution must contain(exactly(
        "Kairo",
        "Alexandria",
        "Tunis",
        "Sousse",
        "Berlin",
        "Hamburg",
        "Köln",
        "Aachen",
        "Danzig",
        "Warschau",
        "Toulouse",
        "Paris"))
    }
    
    "Städtenamen in Deutschland richtig bestimmen" in {
      val solution = sut.alleStädteinDeutschland
      solution must contain(exactly(
      "Berlin",
      "Hamburg",
      "Köln",
      "Aachen"
      ))
    }
    
    "alle Hauptstädte richtig bestimmen" in {
      val solution = sut.alleHauptstädte
      solution must contain(exactly(
       "Kairo",
       "Tunis",
       "Berlin",
       "Warschau",
       "Paris"
      ))
    }
    
    "die größte Stadt richtig bestimmen" in {
      val solution = sut.dieGrößteStadt
      solution must beEqualTo(Stadt("Alexandria", 322345345))
    }
    
    "Summer der Weltbewohner richtig bestimmen" in {
      val solution = sut.summeAllerEinwohnerDerWelt
      solution must beEqualTo(348400115)
    }
  }

}
package collections

// Kontinent ->* Land ->* Stadt
trait Data {
  val kontinente = List(
    Kontinent("Afrika",
      Land("Ägypten",
        Stadt("Kairo", 10000, true),
        Stadt("Alexandria", 322345345)),
      Land("Tunesien",
        Stadt("Tunis", 11341244, true),
        Stadt("Sousse", 1234124))),
    Kontinent("Europa",
      Land("Deutschland",
        Stadt("Berlin", 1000, true),
        Stadt("Hamburg", 200000),
        Stadt("Köln", 13241241),
        Stadt("Aachen", 3)),
      Land("Polen",
        Stadt("Danzig", 1234),
        Stadt("Warschau", 12345, true)),
      Land("Frankreich",
        Stadt("Toulouse", 1234),
        Stadt("Paris", 12345, true))))
}

trait Exercise {
  self: Data =>

  def alleNamenAllerKontinente: List[String] = {
    kontinente
      .map(kontinent => kontinent.name)
  }

  def alleLänderNamen: List[String] = {
    kontinente
      .flatMap(k => k.laender)
      .map(l => l.name)
  }

  def alleStädteNamen: List[String] = {
    kontinente
      .flatMap(k => k.laender)
      .flatMap(l => l.staedte)
      .map(s => s.name)
  }

  def alleStädteinDeutschland: List[String] = {
    kontinente
      .flatMap(k => k.laender.filter(l => l.name == "Deutschland"))
      .flatMap(l => l.staedte)
      .map(s => s.name)

  }

  def alleHauptstädte: List[String] = {
    kontinente
      .flatMap(k => k.laender)
      .flatMap(l => l.staedte.filter(s => s.haupstadt).map(s => s.name))
  }

  def dieGrößteStadt: Stadt = {
    kontinente
      .flatMap(k => k.laender)
      .flatMap(l => l.staedte)
      .sortBy(s => s.einwohner)(Ordering[Int].reverse)
      .head
  }

  def summeAllerEinwohnerDerWelt: Int = {
    kontinente
      .flatMap(k => k.laender)
      .flatMap(l => l.staedte)
      .map(s => s.einwohner)
      .sum
  }

}
package kata.collections

// Kontinent ->* Land ->* Stadt
trait Data {
  val kontinente = List(
    Kontinent("Afrika",
      Land("Ägypten", Stadt("Kairo", 10000, true), Stadt("Alexandria", 322345345)),
      Land("Tunesien", Stadt("Tunis", 11341244, true),
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
    kontinente.map(kontinent => kontinent.name)
    for {
      k <- kontinente
    } yield { k.name }
  }

  def alleLänderNamen: List[String] = {
    kontinente.flatMap(kontinent => (kontinent.laender.map(l => l.name)))

    for {
      k <- kontinente
      l <- k.laender
    } yield { l.name }
  }

  def alleStädteNamen: List[String] = {
    kontinente.flatMap(kontinent => (kontinent.laender.flatMap(l => (l.staedte.map(s => s.name)))))
    for {
      k <- kontinente
      l <- k.laender
      s <- l.staedte
    } yield { s.name }
  }

  def alleStädteinDeutschland: List[String] = {
    kontinente.flatMap(kontinent => (kontinent.laender.flatMap(l => (l.staedte.map(s => s.name)))))
    for {
      k <- kontinente
      l <- k.laender if (l.name == "Deutschland")
      s <- l.staedte
    } yield { s.name }
  }

  def alleHauptstädte: List[String] = {
    for {
      k <- kontinente
      l <- k.laender
      s <- l.staedte if (s.haupstadt)
    } yield { s.name }
  }

  def dieGrößteStadt: Stadt = {
    val result = for {
      k <- kontinente
      l <- k.laender
      s <- l.staedte
    } yield { s }
    result.maxBy(stadt => stadt.einwohner)
  }

  def summeAllerEinwohnerDerWelt: Int = {
    (for {
      k <- kontinente
      l <- k.laender
      s <- l.staedte
    } yield { s.einwohner }).sum
  }
}

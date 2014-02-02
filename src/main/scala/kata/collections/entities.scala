package kata.collections

case class Kontinent(name: String, laender: List[Land])
object Kontinent {
  def apply(name: String, laender: Land*) = new Kontinent(name, laender.toList)
}
case class Land(name: String, staedte: List[Stadt])
object Land {
  def apply(name: String, staedte: Stadt*): Land = new Land(name, staedte.toList)
}
case class Stadt(name: String, einwohner: Int, haupstadt: Boolean = false)

 
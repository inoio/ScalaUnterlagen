package demolens

import scalaz._
import Scalaz._

object lenses extends App {

  case class Person(name: String, address: List[Address])
  case class Address(street: String, nr: Int)

  val pNameLens: Person @> String =
    Lens.lensu[Person, String]({ (p, n) => p.copy(name = n) }, { _.name })

  val personAddresses: Person @> List[Address] =
    Lens.lensu[Person, List[Address]](
      (p, a) => p.copy(address = a),
      _.address)

  val aStreetLens: Address @> String =
    Lens.lensu[Address, String]({ (a, s) => a.copy(street = s) }, { _.street })

  // partial converts total lenses into partial lenses
  def personNthAddress(nth: Int) =
    personAddresses.partial andThen PLens.listNthPLens(nth)

  // ~ is a shortcut for partial
  def personNthAddressStreet(nth: Int) =
    personNthAddress(nth: Int) andThen ~aStreetLens

  val person = Person("Klink", List(Address("Somestreet", 29), Address("AnotherStreet", 30)))

  // get the according lens, call the setter with the person and value 
  val newP = (personNthAddress(1)).set(person, Address("BakerStreet", 221))

  println(newP)
  // trying to access the 3rd address (which does not exist)
  val invalid = (personNthAddress(3)).set(person, Address("BakerStreet", 221))

  println(invalid)
}
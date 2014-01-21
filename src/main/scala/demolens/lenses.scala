package demolens

import scalaz._
import Scalaz._

object lenses extends App {

  case class Person(name: String, address: List[Address])
  object Person {
    val name: Person @> String =
      Lens.lensu[Person, String](
        set = { (p, n) => p.copy(name = n) },
        get = { _.name })

    val address: Person @> List[Address] =
      Lens.lensu[Person, List[Address]](
        set = (p, a) => p.copy(address = a),
        get = { _.address })

    // partial converts total lenses into partial lenses
    def address(nth: Int): Person @?> Address =
      Person.address.partial andThen PLens.listNthPLens(nth)
  }

  case class Address(street: String, nr: Int)
  object Address {
    val street: Address @> String =
      Lens.lensu[Address, String](
        set = { (a, s) => a.copy(street = s) },
        get = { _.street })
  }

  val person = Person("Klink", List(Address("Somestreet", 29), Address("AnotherStreet", 30)))

  // get the according lens, call the setter with the person and value 
  val newP = (Person.address(1)).set(person, Address("BakerStreet", 221))

  println(newP)
  // trying to access the 3rd address (which does not exist)
  val invalid = (Person.address(3)).set(person, Address("BakerStreet", 221))

  println(invalid)
}
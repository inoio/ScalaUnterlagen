package demolens

import scalaz._
import Scalaz._

object lenses extends App {

  case class Person(name: String, address: Address)
  case class Address(street: String, nr: Int)

  val pNameLens: Person @> String =
    Lens.lensu[Person, String]({ (p, n) => p.copy(name = n) }, { _.name })

  val pAddrLens: Person @> Address =
    Lens.lensu[Person, Address]({ (p, a) => p.copy(address = a) }, { _.address })

  val aStreetLens: Address @> String =
    Lens.lensu[Address, String]({ (a, s) => a.copy(street = s) }, { _.street })

  val p = Person("Klink", Address("Somestreet", 29))
  val x = (pAddrLens >=> aStreetLens).set(p, 30)

  val z = (pAddrLens >=> aStreetLens)
  
  println(z.set(p,"AnotherStreet"))
  
}
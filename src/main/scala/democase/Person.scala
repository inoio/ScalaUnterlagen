package democase

object Demo extends App {

  class Person(val name: String, val firstName: String, val hobbies: List[Hobby] = List.empty)

  object Person {
    def apply(name: String, firstName: String, hobbies: List[Hobby]) = 
      new Person(name, firstName, hobbies)
  }

  class Manager(name: String, firstName: String, hobbies: List[Hobby])
    extends Person(name, firstName, hobbies)

  object Manager {
    def apply(name: String, firstName: String, hobbies: List[Hobby]) = 
      new Manager(name, firstName, hobbies)
  }

  case class Hobby(name: String)
  
  def print[A](list: List[A]) : Unit = list.foreach(println _)

  val persons: List[Person] = List(
    Person("Klink", "Markus", List(Hobby("Basteln"), Hobby("Lesen"))),
    Person("Lang", "Markus", List(Hobby("Reiten"), Hobby("Programmieren"))),
    Person("Raimer", "Stephan", List(Hobby("Denken"))))

  val manager: List[Manager] = List(
    Manager("Weilkiens", "Tim", List(Hobby("Joggen"), Hobby("Surfen"))),
    Manager("Oestereich", "Bernd", List(Hobby("Lesen"), Hobby("Managen"))))

  val alle: List[Person] = persons ++ manager
  
  print(persons)
  print(manager)
  print(alle)
  

}
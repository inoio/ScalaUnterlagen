package demotraits

object CoContraVariance extends App {
  import SomeDef._
  var animalSlot = new Slot[Animal1](new Animal1("Something"))
  var dogSlot: Slot[Dog] = new Slot[Dog](new Dog("Dog"))

  //dogSlot = animalSlot

}
object SomeDef {
  class Slot[T](var slot: T) {}

  class Animal1(val name: String) {}
  class Dog(override val name: String) extends Animal1(name) {}
}
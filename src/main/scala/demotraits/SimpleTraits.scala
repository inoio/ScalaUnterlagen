package demotraits

object SimpleTraits extends App{
  
  val dobermann = new Dog("Hasso")
  dobermann.move()
  dobermann.eat()
  println(dobermann.name)

}

trait Animal {
  def classificationName() : String
}

trait Mammal {
  def sleep()
}

trait Legs {
  self : Mammal =>
  def move() : Unit
}

trait Mouth {
  def eat() : Unit
}

class Dog(val name: String) extends Mammal with Legs with Mouth {
  val classificationName = "Dog"
  def move() = println("running")
  def eat() = println("eating")
  def sleep() = println("sleeping")
}
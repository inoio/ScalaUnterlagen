package demococontravariance

object Main extends App  {

}

object Invariance {
  
   class Animal()
   class Cat() extends Animal()
   
   class House[T]()
   
   val cat = new Cat()
   val animal : Animal = cat
   
   val catHouse = new House[Cat]
   // does not compile
  // val animalHouse : House[Animal]= catHouse
  
}

object CoVariance {
  
   class Animal()
   class Cat() extends Animal()
   
   class House[+T]()
   
   val cat = new Cat()
   val animal : Animal = cat
   
   val catHouse = new House[Cat]
   val animalHouse : House[Animal]= catHouse
  
}

object ContraVariance {
  
   class Animal()
   class Cat() extends Animal()
   
   class House[-T]()
   
   val cat = new Cat()
   val animal : Animal = cat
   
   // val catHouse = new House[Cat]
   // does not compile
   //val animalHouse : House[Animal]= catHouse
   
   val animalHouse = new House[Animal]
   val catHouse : House[Cat] = animalHouse
    
  
}
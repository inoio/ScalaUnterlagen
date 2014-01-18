package demopattern

object PatternPartial extends App {

  case class Address(street: String, nr: Int)

  val addresses = List(Address("Grundstrasse", 29),
    Address("Rotherbaum", 38),
    Address("Elbchausee", 1))

  // filter takes a function, a partial function is a function
  println(addresses.filter {
    case Address(str, nr) => nr > 1 
  })
  
  println(addresses.filter { a: Address => a.nr > 1})

}
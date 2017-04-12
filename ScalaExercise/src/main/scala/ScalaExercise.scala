/**
  * Created by a613274 on 05.04.2017.
  */
object ScalaExercise {

  def main(args : Array[String]) = {
    val calc = new Calculator("HP")
    println(calc.color)
    println(calc.add(100, 1000))

    val testFunc = new TestFunctions
    testFunc.timesTwo(11)

    /*
      You can partially apply a function with an underscore, which gives you another function.
      Scala uses the underscore to mean different things in different contexts,
      but you can usually think of it as an unnamed magical wildcard.
      In the context of { _ + 2 } it means an unnamed parameter. You can use it like so: this way you can assign value to add2 (function variable in the end)
     */
    val add2 = testFunc.adder(2, _:Int)
    println(add2(3))

    println(testFunc.capitalizeAll("hello", "world"))
  }

}

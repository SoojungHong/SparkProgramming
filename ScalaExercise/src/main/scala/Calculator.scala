/**
  * Created by a613274 on 05.04.2017.
  */
class Calculator(brand: String) {
  /**
    * A constructor.
    * Q. How come this is a constructor?
    * In Scala the primary constructor is the class’ body and it’s parameter list comes right after the class name.
    */
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  // An instance method.
  def add(m: Int, n: Int): Int = m + n
}
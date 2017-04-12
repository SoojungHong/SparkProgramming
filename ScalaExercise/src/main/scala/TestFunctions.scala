/**
  * Created by a613274 on 06.04.2017.
  */
class TestFunctions {

  //
  def timesTwo(i: Int) = {
    println("hello world")
    println(i * 2)
  }

  //magic of underscore
  def adder(m: Int, n: Int) = m + n

  //variable length arguments
  def capitalizeAll(args: String*) = {
    args.map { arg =>
      arg.capitalize
    }
  }

}


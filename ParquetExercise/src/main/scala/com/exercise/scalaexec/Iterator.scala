package com.exercise.scalaexec

/**
  * Created by a613274 on 13.07.2017.
  *
  * Traits are used to share interfaces and fields between classes. It's similar to Java 8 interface
  * Traits become especially useful as generic types and with abstract methods
  */
trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}

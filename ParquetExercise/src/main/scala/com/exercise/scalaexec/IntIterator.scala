package com.exercise.scalaexec

/**
  * Created by a613274 on 13.07.2017.
  */
class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0
  override def hasNext: Boolean = current < 0
  override def next(): Int = {
    if(hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }

}

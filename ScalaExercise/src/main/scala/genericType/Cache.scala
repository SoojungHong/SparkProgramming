package genericType

/**
  * Created by a613274 on 06.04.2017.
  */
trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def delete(key: K)
}


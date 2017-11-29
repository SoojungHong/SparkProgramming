
/**
  * Created by a613274 on 29.11.2017.
  */

object GenerateData {

  case class MyCaseClass(key: String, group: String, value: Int, someints: Seq[Int], somemap: Map[String, Int])

  def createParquet() = {
    val serviceLocator = new ServiceLocator()
    val sqlContext = new org.apache.spark.sql.SQLContext(serviceLocator.sc)
    import sqlContext.implicits._

    val dataframe = serviceLocator.sc.parallelize(Array(MyCaseClass("a", "vowels", 1, Array(1), Map("a" -> 1)),
      MyCaseClass("b", "consonants", 2, Array(2, 2), Map("b" -> 2)),
      MyCaseClass("c", "consonants", 3, Array(3, 3, 3), Map("c" -> 3)),
      MyCaseClass("d", "consonants", 4, Array(4, 4, 4, 4), Map("d" -> 4)),
      MyCaseClass("e", "vowels", 5, Array(5, 5, 5, 5, 5), Map("e" -> 5)))
    ).toDF()

    // now write it to disk
    dataframe.write.mode("overwrite").parquet("testParquet")
  }
}

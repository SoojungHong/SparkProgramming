/**
  * Created by a613274 on 27.11.2017.
  */
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

class ServiceLocator {

  lazy val spark: SparkSession = SparkSession
                                  .builder()
                                  .getOrCreate()

  lazy val sc: SparkContext = spark.sparkContext

}

/**
  * Created by a613274 on 27.11.2017.
  */
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Recommendation {

  def calculateRecommendations(parameters: RecommendationParameters) = {
    val dataTable = parameters.productsPurchasedByUser.toDF()
      .withColumnRenamed("someints", "int_array")
      .withColumnRenamed("somemap", "map")

    dataTable.show()

    val maxNumProducts = parameters.maxNumberRecommendedProducts

    val selectedProducts = parameters.similarProductThreshold

    val recommendations = dataTable
          .withColumn("doubleVal", col("value"))
          .groupBy("group").sum("value")
          .withColumnRenamed("doubleVal", "myVal")

    //recommendations.show()

    /*
      .withColumn("distance", sqrt(sqDistance(col("user_vector"), col("article_vector"))))
      .select("user_id", "nzz_id", "size", "distance", "pub_date")
      .withColumn("sim_art_under_threshold", when(col("distance") <= parameters.simArtThreshold, col("size")).otherwise(0))
      .groupBy("user_id", "nzz_id", "pub_date").sum("sim_art_under_threshold")
      .withColumnRenamed("sum(sim_art_under_threshold)", "sim_art")
      .where(col("sim_art") >= 3)
      .withColumn("rank", row_number().over(Window.partitionBy("user_id").orderBy(col("sim_art").desc, col("pub_date").desc)))
      .where(col("rank") <= parameters.maxNumberRecommendedArticles)
      .select("user_id", "nzz_id", "rank")
*/
    recommendations
  }

}


class RecommendationParameters extends Serializable {
  var productsPurchasedByUser: DataFrame = _
  var similarProductThreshold: Double = _
  var maxNumberRecommendedProducts: Int = 3
}
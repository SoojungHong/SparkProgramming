/**
  * Created by a613274 on 27.11.2017.
  */
import org.apache.spark.sql.DataFrame

object Recommendation {
  def calculateRecommendations(parameters: RecommendationParameters): DataFrame = {

    val publishedNotBlacklisted = parameters.productsPurchasedByUser
      /*
      .withColumnRenamed("vector", "article_vector")
      .join(parameters.articlesBlacklist, Seq("nzz_id"), "leftanti")
*/

    val lastUserActivity = parameters.maxNumberRecommendedProducts
    /*
      .withColumnRenamed("userid", "user_id")
      .withColumnRenamed("time", "last_time_active")
*/
    val profilesWithArticleVectors = parameters.similarProductThreshold
  /*
      .withColumnRenamed("vector", "user_vector")
      .join(lastUserActivity, "user_id")
      .join(broadcast(publishedNotBlacklisted))
      .where(col("pub_date") > col("last_time_active"))
*/
    val recommendations = profilesWithArticleVectors //ToDo : it has to be dataframe
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
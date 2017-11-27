/**
  * Created by a613274 on 27.11.2017.
  */
import Recommendation._

object RecommendationApp {

  def main(args: Array[String]): Unit = {
    val serviceLocator = new ServiceLocator()
    val spark = serviceLocator.spark
    val cassandraReader = new CassandraReader(spark)
    val settings = new RecommendationSettings()
    val publishedArticles = cassandraReader
      .readTable(settings.productsKeyspace, settings.productsTable)
      /*
      .readTable(settings.publishedArticlesKeyspace, settings.publishedArticlesTable)
      .select("nzz_id", "department", "pub_date")
      .excludeUnwantedArticles()
      .filter(col("pub_date").between(epochSecondsToTimestamp(publishedArticlesPeriod._1), epochSecondsToTimestamp(publishedArticlesPeriod._2))) //only get articles that fall into specified timeframe
      .filter(col("author").isNotNull || col("department") === "Panorama") //filter out agency articles
      .filter(col("department") !== "Marktplatz") //no articles of type "Marktplatz"
      */

    val parameters = new RecommendationParameters
    parameters.similarProductThreshold //ToDo : assign
    parameters.maxNumberRecommendedProducts //ToDo : assign
    parameters.productsPurchasedByUser //ToDo : assign

    val recommendations = calculateRecommendations(parameters)
      /*
      .withColumn("computation_id", lit(computationId))
      .withColumnRenamed("nzz_id", "articleid")
      .withColumn("cms", lit("nzz.ch"))
      .withColumnRenamed("user_id", "userid")
      .union(testArticles(publishedArticles, computationId, spark))
      .cache()
*/
    //recommendations.writeToCassandra(settings.recommendationsKeyspace, settings.recommendationsTable)

  }

}

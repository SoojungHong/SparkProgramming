/**
  * Created by a613274 on 27.11.2017.
  */
import Recommendation._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

object RecommendationApp {

  def main(args: Array[String]): Unit = {
    val serviceLocator = new ServiceLocator()
    val spark = serviceLocator.spark
    val cassandraReader = new CassandraReader(spark)
    val settings = new RecommendationSettings()
    val products = cassandraReader
        .readTable(settings.productsKeyspace, settings.productsTable)
        .select("product_id", "product_price")
        .excludeUnwantedProducts()
        .filter(col("product_price").between(100, 3500))

    products.show()

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

  implicit class Products(products : DataFrame) {
    val CarProductName = "Jeep"

    def excludeUnwantedProducts(): DataFrame = {
      products
        .where(col("product_id").isNull || col("product_name") =!= CarProductName)
        .where(col("product_id").isNull || col("product_price") > 2000)
    }

  }

}

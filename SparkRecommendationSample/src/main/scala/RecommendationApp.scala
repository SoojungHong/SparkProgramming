/**
  * Created by a613274 on 27.11.2017.
  */
import Recommendation._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions._
import GenerateData._
import CassandraImplicit._

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

    //products.show()

    createParquet()

    val parameters = new RecommendationParameters
    parameters.similarProductThreshold = 2
    parameters.maxNumberRecommendedProducts = 1
    parameters.productsPurchasedByUser = spark.read.parquet("testParquet")
    //parameters.productsPurchasedByUser.show()

    val recommendations = calculateRecommendations(parameters)
        .withColumn("testcol", lit("4"))
        .select("group", "testcol")
        .cache()
      /*
      //final cleaning to indicate only mattered columns
      .withColumn("computation_id", lit(computationId))
      .withColumnRenamed("nzz_id", "articleid")
      .withColumn("cms", lit("nzz.ch"))
      .withColumnRenamed("user_id", "userid")
      .union(testArticles(publishedArticles, computationId, spark))
      .cache()
       */

    recommendations.show()

    recommendations.writeToCassandra("testkeyspace", "testtable")

    print("DONE, Bye")
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

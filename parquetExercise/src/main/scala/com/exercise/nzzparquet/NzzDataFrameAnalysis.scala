package com.exercise.nzzparquet

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.sql.functions.explode

/**
  * Created by a613274 on 11.05.2017.
  */
object NzzDataFrameAnalysis {

  implicit class NzzTdAgentAnalysis(df : DataFrame) {

    //Show Schema of DataFrame
    def showDataFrameSchema() : Unit = {
      println("[NzzTdAgentAnalysis:showDataFrameSchema()]============================= ")
      df.printSchema()
    }

    //functions.explode()
    def explodeFunctionExercise(sc : SparkContext): Unit = {
      val conf = new SparkConf().setAppName("NzzParquet").setMaster("local")
      //val sc = new SparkContext(conf)
      val sqlContext = new org.apache.spark.sql.SQLContext(sc)

      import sqlContext.implicits._

      val test = sqlContext.read.json(sc.parallelize(Seq("""{"a":1,"b":[2,3]}""")))
      test.printSchema()
      val flattened = test.withColumn("b", explode($"b"))
      flattened.printSchema()
      flattened.show()
    }

    //withColumn - add column
    def withColumnExercise(sc : SparkContext) = {
      val sqlContext = new SQLContext(sc)
      import sqlContext.implicits._
      import org.apache.spark.sql.functions._
      val df = sc.parallelize(Seq( (4, "blah", 2), (2, "", 3), (56, "foo", 3), (100, null, 5))).toDF("A", "B", "C")
      df.show()
      val newDF = df.withColumn("D", when($"B".isNull or $"B" === "", 0).otherwise((1)))
      newDF.show()
    }

    //groupByExercise
    def groupByExercise(sc : SparkContext) = {
      val sqlContext = new SQLContext(sc)

      //df.groupBy("browser", "campaign", "channel").count().show()
      df.groupBy("channel").count().show()

      import org.apache.spark.sql.functions._
      df.groupBy("channel")
        .agg(max("browser") as "max_browser")
        .sort(asc("max_browser"))
        .show()

    }

    //join code
    /*
      val usersWithUnreadArticles = usersWithAllArticles
 +      .join(broadcast(publishedArticlesReadByUser),
 +        usersWithAllArticles(UserId) === publishedArticlesReadByUser("userid") &&
 +          usersWithAllArticles("nzz_id") === publishedArticlesReadByUser("nzzid"),
 +        "left_outer"
 +      )
 +      .where(col("nzzid").isNull)
 +      .drop("nzzid").drop("userid").drop("time")

     */

  }

}

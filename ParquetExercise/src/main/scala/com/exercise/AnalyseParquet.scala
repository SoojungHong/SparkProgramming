package com.exercise

import org.apache.spark.sql.{DataFrame, GroupedData}
import com.exercise.Setting._
import org.apache.spark.sql.functions._

import scala.reflect.internal.util.TableDef.Column

/**
  * Created by a613274 on 31.03.2017.
  */
object AnalyseParquet {

  implicit class AnalysisOperations(df : DataFrame) {

    //Show Schema of DataFrame
    def showDataFrameSchema() : Unit = {
      df.printSchema()
    }

    //get rows (data frame) that column channel is 'prod-nzz'
    def getPartialDataFrame() : DataFrame = {
      df.filter(df.col("channel") !== "prod-nzz")
    }

    //filter using channel column and select only browser column
    def showChannelAndBrowserColumn(): DataFrame = {
      df.filter(df.col("channel") !== "prod-nzz")
        .select("browser")
    }

    //groupBy column and apply aggregate function and give a name of the result of aggregation
    def groupByAndAgg() = {
      val groupedSessions: GroupedData = df.filter(df.col("channel") !== "prod-mg").groupBy("domain")
      groupedSessions.count().show()
      groupedSessions.agg(min("date_time").alias("MinTime")).show()
      println(groupedSessions.agg(max("date_time").alias("MaxTime")).collect()(0))
    }

    // $"column_name" for select
    def testSelect() = {
      df.select("channel", "browser").show()
    }

    //TODO : Clean up this


    //val f : () => Unit  = () => println("Hola Mundo")


    /*
    val countDistinctDF = nonNullDF.select($"firstName", $"lastName")
      .groupBy($"firstName", $"lastName")
      .agg(countDistinct($"firstName") as "distinct_first_names")
    display(countDistinctDF)
*/
    def testLit() = {
      //lit(x) is treat x as literal
    }


    /*
     *
    val datesToConvert = datesBetween(lastConvertedDate.plusDays(1), yesterday)

    datesToConvert.foreach(date => convertByDate(settings, serviceLocator, date, jsonSchemaWithServerTime))

    def datesBetween(startDate: DateTime, endDate: DateTime): Seq[LocalDate] = {
    val startDateLocal = startDate.toLocalDate
    val endDateLocal = endDate.toLocalDate
    var dates = scala.collection.mutable.ArrayBuffer[LocalDate](startDateLocal)
    while (!dates.last.equals(endDateLocal)) {
      dates += dates.last.plusDays(1)
    }
    dates
  }
     */
    def testForEach() = {
      var myTestSeq : Seq[Int] = List(1, 2, 3, 4, 5)
      var newSeq : Seq[Int] = null
      myTestSeq.foreach((i:Int) => {  println(i * 2) }) //numbers.foreach((i: Int) => i * 2)
      println(myTestSeq)
    }

    //?? run forever??
    def testDFforEach() = {
      var filtered = df.filter(col("channel") === "prod-mg").groupBy("domain")
      filtered.count().foreach(println)
    }

    //groupByTest
    def testGroupBy() = {
      //df.show(10)
      df.groupBy("channel").count.show(10) // .groupBy($"incorrectFields").count.show(100,false)
    }

    /*
ToDo : drop()

+    val usersWithUnreadArticles = usersWithAllArticles
+      .join(articlesReadByUser,
+        usersWithAllArticles(UserId) === articlesReadByUser("userid") &&
+          usersWithAllArticles("nzz_id") === articlesReadByUser("nzzid"),
+        "left_outer"
+      )
+      .where(col("nzzid").isNull)
+      .drop("nzzid").drop("userid").drop("time")


ToDo : join() and renamed()

+    val articlesWithEditorialScore = usersWithUnreadArticles
+      .join(editorialScore, "nzz_id")
+      .withColumnRenamed("score_now", "editorial_score")
+      .withColumnRenamed("department", Category)

ToDo : understand this code

+    val userScoreCalculated = withCategoryScores
+      .withColumn("user_score", (lit(category_score_weight) * col("normalized_category_score")) + col("normalized_editorial_score"))
+    //ops! I changed from multiplicative to additive!
+
+    userScoreCalculated
+      .withColumn("rank", rank().over(Window.partitionBy(UserId).orderBy(desc("user_score"))))
+      .select(
+        col(UserId).as("userid"),
+        lit("nzz.ch").as("cms"),
+        col("nzz_id").as("articleid"),
+        col("rank"))
+  }

ToDo : cache for what?

+    val cassandraReader = new CassandraReader(sqlContext)
+    val articles = cassandraReader
+      .readTableWithTimeConversion(settings.cassandraKeyspace, "nzz_publishedarticles", "pub_date", "unix_timestamp")
+      .dropDuplicates(Seq("nzz_id"))
+      .filter(lit(publishedArticlesPeriod._1) < col("unix_timestamp") && col("unix_timestamp") <= lit(publishedArticlesPeriod._2))
+      .cache()

     */
  }

}

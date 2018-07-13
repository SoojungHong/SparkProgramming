package com.exercise.nzzparquet

import org.apache.spark.{SparkConf, SparkContext}
import com.exercise.nzzparquet.NzzDataFrameAnalysis._
import org.apache.spark.sql.functions

import scala.reflect.internal.util.TableDef.Column

/**
  * Created by a613274 on 11.05.2017.
  */
object NzzParquet {

  def main(args : Array[String]) = {
    System.setProperty("hadoop.home.dir", "c:\\winutil\\")
    val conf = new SparkConf().setAppName("NzzParquet").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._
    sqlContext.setConf("spark.sql.parquet.mergeSchema","true") //set the given Spark SQL configuration property, When true, the Parquet data source merges schemas collected from all data files

    val parqfile = sqlContext.read.parquet("C:/Users/a613274/Backup/Soojung/NZZdata/nzztest.parquet") //local
    parqfile.show(20)

    var filteredDF = parqfile.filter($"channel" === "prod-nzz") //to enable this, import sqlContext.implicits._
    //filteredDF.withColumn("username", functions.explode($"username"))

    //import org.apache.spark.sql.functions._
    //filteredDF.withColumn("SoojungColumn", when($"channel" === "prod-nzz", 100).otherwise(1))
    //filteredDF.select($"browser", $"campaign", $"channel").show(20)
    //filteredDF.show(20)

    //parqfile.showDataFrameSchema()

    //parqfile.explodeFunctionExercise(sc)

    //parqfile.withColumnExercise(sc)

    parqfile.groupByExercise(sc)
  }

}

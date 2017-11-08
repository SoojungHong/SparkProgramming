package com.exercise

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SQLContext}

import scala.reflect.internal.util.TableDef.Column
import com.exercise.AnalyseParquet._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.hive.orc._

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

/**
  * Created by shong on 17.02.2017.
  */
object ParquetExercise {

  def doAnalysisOperations(parqfile : DataFrame, sc:SparkContext): Unit = {
    //println("[INFO] Showing the scheme of dataframe ")
    //parqfile.showDataFrameSchema()

    //println("[INFO] Filtering dataframe using column 'channel' is 'prod-nzz' ")
    //parqfile.doFilterDataFrame().show(10)

    //println("[INFO] Filtering dataframe using column 'channel' is 'prod-nzz' and select 'browser' ")
    //parqfile.doFilterAndSelect().show(10)

    //test : groupBy and aggregate
    //println("[INFO] do GroupBy and Aggregate ")
    //parqfile.doGroupByAndAggregate()

    //test : repartition on RDD
    //println("[INFO] Returns a new DataFrame that has exactly numPartitions partitions.")
    //parqfile.doCoalesceTest(16)

    //println("[INFO] drop column ")
    //parqfile.doDrop()

    //println("[Log] Test find() and getOrElse() methods")
    //parqfile.doFindAndGetOrElse()

    //parqfile.parquetExercise(sc)

    //parqfile.doUDFexercise(sc)

    //parqfile.dropDuplicate(sc)

    //parqfile.exerciseAs(sc)

    //parqfile.exerciseSeq()

    parqfile.exerciseSomeOption()

  }

  def main(args : Array[String]) = {
    //-----------------------
    // setup Spark Context
    //-----------------------
    System.setProperty("hadoop.home.dir", "c:\\winutil\\")
    val conf = new SparkConf().setAppName("ParquetExercise").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc) //this is ordinary sql context to read parquet
    //val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc) //to use Hive, I should use sqlContext using HiveContext

    val parqfile = sqlContext.read.parquet("C:/Users/a613274/Backup/Soojung/NZZdata/nzztest.parquet") //local
    //parqfile.show(20)

    //----------------------------
    // Exercise on Parquet Files
    //----------------------------
    doAnalysisOperations(parqfile, sc)
  }

}

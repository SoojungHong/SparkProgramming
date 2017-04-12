package com.exercise

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SQLContext}

import scala.reflect.internal.util.TableDef.Column
import com.exercise.AnalyseParquet._

/**
  * Created by shong on 17.02.2017.
  */
object ParquetExercise {

  def doAnalysisOperations(parqfile : DataFrame): Unit = {
    //show schema of data frame
    //parqfile.showDataFrameSchema()

    //get partial part of data frame
    var partial = parqfile.getPartialDataFrame()
    println("Count of Partial Data Frame : " + partial.count())
    partial.show()

    //test groupBy and aggregate
    parqfile.groupByAndAgg()

    //test select and $"ABC"
    parqfile.testSelect()

    //TODO : Clean-up below
    //val myData = parqfile.cache()
    //myData.showChannelAndBrowser.show()
    //myData.groupByAndAgg.show(20) //FIXME : running this code do nothing or infinite loop?
    //myData.testForEach()
    //myData.testGroupBy()
  }

  def main(args : Array[String]) = {
    System.setProperty("hadoop.home.dir", "c:\\winutil\\")
    val conf = new SparkConf().setAppName("ParquetExercise").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val parqfile = sqlContext.read.parquet("C:/Users/a613274/Backup/Soojung/NZZdata/nzztest.parquet") //local
    //parqfile.show(20)

    doAnalysisOperations(parqfile)
  }

}

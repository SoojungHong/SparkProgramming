/**
  * Created by a613274 on 27.11.2017.
  */
/*
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
*/

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import org.apache.spark.sql._


import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}


class ServiceLocator {
/* This is code for running on Cloud
  lazy val spark: SparkSession = SparkSession
                                  .builder()
                                  .getOrCreate()

  lazy val sc: SparkContext = spark.sparkContext
*/

  //to run Spark on Windows locally
  System.setProperty("hadoop.home.dir", "c:\\winutil\\")
  val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
  val sc = spark.sparkContext // Just used to create test RDDs
}

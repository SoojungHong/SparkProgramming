package com.exercise.spark

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._


/**
  * Created by a613274 on 03.07.2017.
  */
object SparkExercise {

  def doBroadcastJoin(sc : SparkContext, sqlContext : SQLContext): Unit = {
    val employeesRDD = sc.parallelize(Seq(
      Employee("Mary", 33, "IT"),
      Employee("Paul", 45, "IT"),
      Employee("Peter", 26, "MKT"),
      Employee("Jon", 34, "MKT"),
      Employee("Sarah", 29, "IT"),
      Employee("Steve", 21, "Intern")
    ))

    val departmentsRDD = sc.parallelize(Seq(
      Department("IT", "IT  Department"),
      Department("MKT", "Marketing Department"),
      Department("FIN", "Finance & Controlling")
    ))

    import sqlContext.implicits._ //To use toDF, I need to import this

    val employeesDF = employeesRDD.toDF("name", "age", "depId")
    val departmentsDF = departmentsRDD.toDF("id", "name")

    // materializing the department data
    val tmpDepartments = broadcast(departmentsDF.as("departments"))

    employeesDF.join(broadcast(tmpDepartments),
      $"depId" === $"id",  // join by employees.depID == departments.id
      "inner").show()
  }

  def main(args : Array[String]) = {
    //-----------------------
    // setup Spark Context
    //-----------------------
    System.setProperty("hadoop.home.dir", "c:\\winutil\\")
    val conf = new SparkConf().setAppName("ParquetExercise").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    //this is ordinary sql context to read parquet
    //val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc) //to use Hive, I should use sqlContext using HiveContext
    val parqfile = sqlContext.read.parquet("C:/Users/a613274/Backup/Soojung/NZZdata/nzztest.parquet") //local
    parqfile.show(20)

    //---------------------
    // do Broadcast Join
    //---------------------
    doBroadcastJoin(sc, sqlContext)
  }

  case class Employee(name:String, age:Int, depId: String)
  case class Department(id: String, name: String)

}

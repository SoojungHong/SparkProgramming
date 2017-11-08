package com.exercise

import org.apache.spark.sql.{DataFrame, GroupedData, UserDefinedFunction}
import com.exercise.Setting._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.hive.orc._
import org.apache.spark.sql.functions.{col, dense_rank, desc}

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
    def doFilterDataFrame() : DataFrame = {
      df.filter(df.col("channel") !== "prod-nzz")
    }

    //filter using column (called channel)'s value is 'prod-nzz' and select only 'browser' column
    def doFilterAndSelect(): DataFrame = {
      df.filter(df.col("channel") !== "prod-nzz")
        .select("browser")
    }

    //groupBy column and apply aggregate function and give a name of the result of aggregation
    //variable 'groupedSession' is type of GroupedData
    def doGroupByAndAggregate() = {
      val groupedSessions: GroupedData = df.filter(df.col("channel") !== "prod-mg").groupBy("domain")
      groupedSessions.count().show()
      groupedSessions.agg(min("date_time").alias("MinTime")).show()
      println(groupedSessions.agg(max("date_time").alias("MaxTime")).collect()(0))
    }

    // $"column_name" for select
    def doSelect() = {
      df.select("channel", "browser").show()
    }

    //DataFrame.coalesce()
    // Specification :
    // DataFrame	coalesce(int numPartitions)
    // Returns a new DataFrame that has exactly numPartitions partitions.
    def doCoalesceTest(numPart : Integer) : Unit = {
      println(" The number of partition on RDD : " + df.rdd.partitions.size);
      val newRDD = df.rdd.coalesce(numPart)
      println(" The number of partition on newRDD : " + newRDD.partitions.size);
    }

    //Drop column
    def doDrop() = {
      df.select("c_color", "channel", "browser")
        .drop("c_color")
        .show()
    }



    def exerciseAs(sc : SparkContext) = {

      val sqlContext = new org.apache.spark.sql.SQLContext(sc)
      import sqlContext.implicits._

      val data = sc.parallelize(List(("Foo",41,"US",3),
        ("Foo",39,"UK",1),
        ("Bar",57,"CA",2),
        ("Bar",72,"CA",2),
        ("Baz",22,"US",6),
        ("Baz",36,"US",6))).toDF("x","y","z","count")

      data.show()

      val newData = data.where(col("x").isNotNull)
                        .drop("y") //dropping whole column with column name "y"
                        .select("x", "z", "count")
                        .as[(String, String, Int)]
                        .groupBy(col("x") === "Foo")

    //  newData.show()

      val words = Array("one", "two", "two", "three", "three", "three")
      val wordPairsRDD = sc.parallelize(words).map(word => (word, 1)) //return map
      wordPairsRDD foreach(x => println(x._1 + "-->" + x._2)) //print map

      val wordCountsWithGroup = wordPairsRDD
        .groupByKey()
        .map(t => (t._1, t._2.sum)) //here return map
        .collect() //To print all elements on the driver, one can use the collect() method to first bring the RDD to the driver node thus: rdd.collect().foreach(println).

      /*
      This can cause the driver to run out of memory, though, because collect() fetches the entire RDD to a single machine;
      if you only need to print a few elements of the RDD, a safer approach is to use the take(): rdd.take(100).foreach(println).
       */

      wordCountsWithGroup foreach(x => println(x._1 + "==>" + x._2))

    }
    // exercise of .as
    /*
            .where(col("vector").isNotNull)
            .drop("word")
            .select("nzz_id", "pub_date", "vector")
   -        .as[(String, Timestamp, Vector)].groupByKey(row => (row._1, row._2)).agg(VectorSumarizer().toColumn)
   +        .as[(String, Timestamp, Vector)].groupByKey(row => (row._1, row._2)).agg(VectorSummarizer().toColumn)
            .toDF("key", "vector")
            .withColumn("nzz_id", col("key._1"))
            .withColumn("pub_date", col("key._2"))
     */


    def exerciseSeq() = {
      /*
      println("Step 1: How to initialize a Sequence with 3 elements")
      val seq1: Seq[String] = Seq("Plain Donut","Strawberry Donut","Chocolate Donut")
      println(s"Elements of seq1 = $seq1")
      */

      println("\nStep 5: How to initialize an empty Sequence")
      val emptySeq: Seq[String] = Seq.empty[String]
      println(s"Empty Sequence = $emptySeq")
    }

    def exerciseSomeOption() = {
      val x: Option[String] = Some("asdasd")
      println(x)

      val y: Option[String] = Some(null)
      println(y)
    }

    //How to use _ in Scala
    def exercise_() = {
      /*
      import scala._    // Wild card -- all of Scala is imported
      import scala.{ Predef => _, _ } // Exception, everything except Predef
      def f[M[_]]       // Higher kinded type parameter
      def f(m: M[_])    // Existential type
      _ + _             // Anonymous function placeholder parameter
      m _               // Eta expansion of method into method value
      m(_)              // Partial function application
      _ => 5            // Discarded parameter
      case _ =>         // Wild card pattern -- matches anything
        val (a, _) = (1, 2) // same thing
        for (_ <- 1 to 10)  // same thing
          f(xs: _*)         // Sequence xs is passed as multiple parameters to f(ys: T*)
      case Seq(xs @ _*) // Identifier xs is bound to the whole matched sequence
      var i: Int = _    // Initialization to the default value
      def abc_<>!       // An underscore must separate alphanumerics from symbols on identifiers
      t._2              // Part of a method name, such as tuple getters
      */
    }

    def doFindAndGetOrElse(): Unit = {

      val nums = List(5, 1, 4, 3, 2)
      val filtered = nums.filter(_ > 2)
      println("Filtered number : " + filtered)
      println("Find 3 : " + nums.find(_ == 3) != None)

      val a:Option[Int] = Some(5)
      val b:Option[Int] = None

      val findResult = nums.find(_ == 9)
      print("Return : " + findResult)
      println("a.getOrElse(0): " + a.getOrElse(0) )
      //println("b.getOrElse(throw new IllegalStateException" + b.getOrElse(throw new IllegalStateException("Exception")))
      println("findResult.getOrElse(): " + findResult.getOrElse(throw new IllegalStateException("Exception")))
    }

    //parquet() method exercise
    def parquetExercise(sc : SparkContext): Unit = {
/* //Warning! Case Class should be outside of method
      // Create the case classes for our domain
      case class Department(id: String, name: String)
      case class Employee(firstName: String, lastName: String, email: String, salary: Int)
      case class DepartmentWithEmployees(department: Department, employees: Seq[Employee])
*/
      // Create the Departments
      val department1 = new Department("123456", "Computer Science")
      val department2 = new Department("789012", "Mechanical Engineering")
      val department3 = new Department("345678", "Theater and Drama")
      val department4 = new Department("901234", "Indoor Recreation")

      // Create the Employees
      val employee1 = new Employee("michael", "armbrust", "no-reply@berkeley.edu", 100000)
      val employee2 = new Employee("xiangrui", "meng", "no-reply@stanford.edu", 120000)
      val employee3 = new Employee("matei", null, "no-reply@waterloo.edu", 140000)
      val employee4 = new Employee(null, "wendell", "no-reply@princeton.edu", 160000)

      // Create the DepartmentWithEmployees instances from Departments and Employees
      val departmentWithEmployees1 = new DepartmentWithEmployees(department1, Seq(employee1, employee2))
      val departmentWithEmployees2 = new DepartmentWithEmployees(department2, Seq(employee3, employee4))
      val departmentWithEmployees3 = new DepartmentWithEmployees(department3, Seq(employee1, employee4))
      val departmentWithEmployees4 = new DepartmentWithEmployees(department4, Seq(employee2, employee3))

      val sqlContext = new org.apache.spark.sql.SQLContext(sc)
      import sqlContext.implicits._
      //create the first DataFrame from a List of the Case Classes
      val departmentsWithEmployeesSeq1 = Seq(departmentWithEmployees1, departmentWithEmployees2)
      val df1 = departmentsWithEmployeesSeq1.toDF()
      df1.show()

      val departmentsWithEmployeesSeq2 = Seq(departmentWithEmployees3, departmentWithEmployees4)
      val df2 = departmentsWithEmployeesSeq2.toDF()
      //display(df2)
      df2.show()

      //union of df1 and df2
      val unionDF = df1.unionAll(df2)
      unionDF.show()
      unionDF.write.parquet("databricks-df-example.parquet")

      //read a DataFrame from the Parquet file
      val parquetDF = sqlContext.read.parquet("databricks-df-example.parquet")
    }

    //ToDo : 'left outer join' and all other join
    def doLeftOuterJoin() : Unit = {

    }

    //all kinds of join
    /*
    val withRotationIndex = withCategoryScores
          .join(rotationIndex, Seq(Category), "left_outer")
          .na.fill(0.75)
          .drop("art")
          .drop("avgTime")
          .drop("r1")
          .drop("r2")
    */


    //UserDefinedFunction (UDF)
    //User-Defined Functions (aka UDF) is a feature of Spark SQL to define new Column-based functions
    //that extend the vocabulary of Spark SQL’s DSL for transforming Datasets.
    def doUDFexercise(sc : SparkContext): Unit = {
      val sqlContext = new org.apache.spark.sql.SQLContext(sc)
      import sqlContext.implicits._
      val dataset = Seq((0, "hello"), (1, "world")).toDF("id", "text")

      //define regular scala function
      val upper : String => String = _.toUpperCase //To understand => operator, import org.apache.spark.sql.functions.udf

      //Define the UDF that wraps the upper scala function defined above
      //you could also define the function in place
      import org.apache.spark.sql.functions.udf
      val upperUDF = udf(upper)

      dataset.withColumn("upper", upperUDF('text)).show()
      dataset.withColumn("lower", my_udf('text)).show()
    }

    def my_udf : UserDefinedFunction = {
      udf((input: String) => {input.toLowerCase()})
    }

    //dropDuplicate
    def dropDuplicate(sc : SparkContext) = {

      val sqlContext = new org.apache.spark.sql.SQLContext(sc)
      import sqlContext.implicits._

      val data = sc.parallelize(List(("Foo",41,"US",3),
        ("Foo",39,"UK",1),
        ("Bar",57,"CA",2),
        ("Bar",72,"CA",2),
        ("Baz",22,"US",6),
        ("Baz",36,"US",6))).toDF("x","y","z","count")

      data.show()
      data.dropDuplicates(Array("x", "count")).show() //This case row contains same "x" and "count", then drop

    }

  }

  // Create the case classes for our domain
  case class Department(id: String, name: String)
  case class Employee(firstName: String, lastName: String, email: String, salary: Int)
  case class DepartmentWithEmployees(department: Department, employees: Seq[Employee])

}

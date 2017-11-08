package com.exercise.scalaexec

import org.apache.http.protocol.ExecutionContext

/**
  * Created by a613274 on 18.05.2017.
  */
object scalaTest {

  def main(args : Array[String]) = {
    println("Scala Test")

    /* //TEST
    //define function called toWeekDay, input is string type and return is int
    val toWeekDay: (String => Int) = (dateString: String) => dateString.length
    println(toWeekDay("mehh"))

    val ZeroDate: String = new String("babo")
    println(ZeroDate)

    listDirectories()

    caseClassExercise()

    traitsExercise()

    testInputParam("haha", 1, paramFunction)

    //implicit parameter
    implicit val multiplier = 2
    print("----> "  + testImplicitParam)

    import org.apache.spark.sql.functions.udf
    val predict = udf((score:Double) => if(score > 0.5) true else false)
    */
    println("--- exercise : implicit context")
    print(add(8))
  }

  //test of implicit parameter - Scala “implicits” allow you to omit calling methods or referencing variables directly but instead rely on the compiler to make the connections for you.
  def testImplicitParam(implicit by:Int): Int = {
    return by * 100
  }

  //ToDo : Aug 15
  //1. some
  /*
    val serviceLocator = new ServiceLocator("Category Score", Some(settings.cassandraHost))

   */

  //2. ._1 , ._2 , _* how to use this format?
  // val startTimeSeconds = timeRange._1
  // val endTimeSeconds = timeRange._2
  // var data = spark.read.option("mergeSchema", "true").parquet(files: _*)

  //3. multiple input arguments?
  /*
   fullArticleViews
      .categoryScore(categories)(new TypeOfDayCategoryScore(MinWeekdayTotal, MinWeekendTotal))
      .withColumn("time", lit(nowTimestamp))
      .write.overwriteParquet(parquetPath(settings.categoryScoreDir + "/" + TypeOfDayCategoryScoreDir, nowDate, FileNameDateFormatter, CategoryScoreFilePrefix))


  def categoryScore(categories: DataFrame)(implicit strategy: CategoryScoreStrategy): DataFrame = {

    val withGroupingColumns = df
      .filter(col(Category) !== "")
      .join(broadcast(categories), Category)
      .addGroupingColumns()

  */

  //implicit context
    //implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
    //def getEmployee(id: Int)(implicit e: ExecutionContext): Future[Employee] = ???

  implicit val n : Int = 5
  def add(x : Int)(implicit y : Int) = x + y


    //exercise udf
  // def extractTextFromHtml: UserDefinedFunction = udf((html: String) => Jsoup.parse(html).text().replaceAll("\\p{P}", ""))
  /*
  def myFunc: (String => String) = { s => s.toLowerCase }

import org.apache.spark.sql.functions.udf
val myUDF = udf(myFun)

val newDF = df.withColumn("newCol", myUDF(df("oldCol")))
   */

  // passing function as a parameter
  // https://twitter.github.io/scala_school/type-basics.html
  def testInputParam(path : String, vl: Int, f : Int => String): Unit = {
    println("Given path : " + path + " f : " + f(vl))
  }

  def paramFunction(myVal : Int): String = {
    myVal * 10
    return myVal.toString
  }

  //for each test
  def listDirectories() = {
    System.out.println("List and Seq exercise.. ")
    val seq = Seq("a", "b")
    var stringList: List[String] = List()
    stringList = List("hahahaha", "hoho", "hey")
    stringList.foreach(status =>
      if (status.length > 0) {
        System.out.println(status.length);
      }
    )

    //return seq
  }

  //trait exercise
  def traitsExercise(): Unit = {
    val iterator = new IntIterator(10)
    println(iterator.next())
    println(iterator.next())
  }

  //case class
  def caseClassExercise(): Unit = {
    val myCaseClassObj1 = MyCaseClass(1, 100)
    val myCaseClassObj2 = MyCaseClass(2, 200)
    if(myCaseClassObj1 == myCaseClassObj2) {
      println("it's same")
    } else {
      println("it's not same")
    }
  }

}


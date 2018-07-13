import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by soojunghong on 7/13/18.
  */
object mapTest {

  def main(args:Array[String]) = {
    val spark = SparkSession.builder.appName("mapExample").master("local").getOrCreate()
    val data = spark.read.textFile("spark_test.txt").rdd
    val mapFile = data.map(line => (line, line.length))
    mapFile.foreach(println)

    //flatMap - for each input elements, many elements in an output RDD
    val data = spark.read.textFile("spark_text.txt").rdd
    val flatmapFile = data.flatMap(lines => lines.split(" "))
    flatmapFile.foreach(println)

    //filter - return RDD containing only the elements that meet a predicate - narrow operatoin because it doesn't suffle data from one partition to many partitions
    val data = spark.read.textFile("spark_test.txt").rdd
    val mapFileForFilter = data.flatMap(lines => lines.split(" ")).filter(value => value=="spark")
    println(mapFileForFilter.count()) //count is action

    //union
    val rdd1 = spark.sparkContext.parallelize(Seq((1, "jan", 2016), (3, "nov", 2014), (16, "feb", 2014)))
    val rdd2 = spark.sparkContext.parallelize(Seq((1, "dec", 2016), (3, "sep", 2014)))
    val rdd3 = spark.sparkContext.parallelize(Seq((6, "dec", 2011), (3, "may", 2015)))
    val rddUnion = rdd1.union(rdd2).union(rdd3)
    rddUnion.foreach(println)

    //intersace - only common element
    val rddCommon = rdd1.intersaction(rdd2)
    rddCommon.foreach(println)

    //distinct - return new dataset that contains distinct element of the source dataset , it is helpful to remove duplicate data
    val distinctRDD = rdd1.distinct()
    println(result.collect.mkString(", "))

    //groupByKey() - on dataset of (K,V) pairs. Data is shuffled across the key value K in another RDD.
    // (disadvantage) In transformation, lots of unnecessary data get to transfer over the network
    val data = spark.sparkContext.parallelize(Array(('k',5), ('s',3), ('s', 4), ('p', 7)), 3)
    val group = data.groupByKey().collect() //collect() action will return all the elements of the dataset as an array
    group.foreach(println)

    //reduceByKey(func, [numTasks])
    //reduceByKey on dataset(K,V) the pair on the same machine with the same key are combined before data is suffled.
    val words = Array("one","two","two","four","five","six","six","eight","nine","ten")
    val data = spark.sparkContext.parallelize(words).map(w => (w,1)).reduceByKey(_+_)
    data.foreach(println)

    //sortByKey() - sort according to the key K
    val data = spark.sparkContext.parallelize(Seq(("maths",52), ("english",75), ("science",82), ("computer",65), ("maths",85)))
    val sorted = data.sortByKey()
    sorted.foreach(println)

    //join - combine fields with common values
    val data = spark.sparkContext.parallelize(Array(('A',1),('B',2),('C',3)))
    val data = spark.sparkContext.parallelize(Array(('A',1),('B',2),('C',3), ('D',4)))
    val result = data.join(data2)
    println(result.collect().mkString(","))

    //coalese() - to avoid the full shuffling of data, we use existing partition so that less data is shuffled - it will decrease the number of partition
    val result = rdd1.coalesce(2)


    //action - transformation creates RDDs from each other, we want to work with actual dataset. actions are operation that give non-RDD values
    //values of action are stored to drivers or to external storage systems. action is one of way to send data from executor to driver.

    //count - return the number of elements in RDD
    val data = spark.read.textFile("spark_text.txt").rdd
    val mapFile = data.flatMap(lines => lines.split(" ")).filter(value => value=="spark")
    println(mapFile.count())

    //collect() - common and simplest operation that return the entire RDD content to driver program
    //collect() is unit testing where the entire RDD is expected to fit in memory
    val data = spark.sparkContext.parallelize(Array(('A',1),('b',2),('c',3)))
    val data2 =spark.sparkContext.parallelize(Array(('A',4),('A',6),('b',7),('c',3),('c',8)))
    val result = data.join(data2)
    println(result.collect().mkString(","))

    //take(n) - returns n number of element from RDD
    val data = spark.sparkContext.parallelize(Array(('A',1),('b',2),('c',3)))
    val group = data.groupByKey().collect()
    val twoRec = result.take(2)
    twoRec.foreach(println)

    //top() - extract top element
    val res = data.top(3)

    //countByValue() - return how many times each element occur in RDD
    val data = spark.read.textFile("spark_text.txt").rdd
    val result = data.map(line => (line, line.length)).countByValue()
    //countByValue() return hashmap of (K,V) pairs with the count of key

    //reduce() - take two elements as input from RDD and produce the output of the same type as input
    //simple form of reduce() is addition
    val rdd1 = spark.sparkContext.parallelize(List(20, 30, 34,28))
    val sum = rdd1.reduce(_+_)

    //fold - it does same as reduce but it takes zero value as input , it can be used for initial call for each parition
    // reduce() throw exception on empty collection, but fold return empty collection
    val rdd1 = spark.sparkContext.parallelize(List(("maths", 80),("science", 90)))
    val additionalMarks = ("extra", 4)
    val sum = rdd1.fold(additionalMarks){(acc, marks) => val add = acc._2 + mark._2("total", add)}

  }

}

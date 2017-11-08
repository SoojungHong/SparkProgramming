/**
  * Created by a613274 on 08.11.2017.
  *
  * @reference : https://docs.databricks.com/spark/latest/dataframes-datasets/introduction-to-dataframes-scala.html
  */


import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}


object ScalaDataFrame {

  def main(args: Array[String]) {
    System.setProperty("hadoop.home.dir", "c:\\winutil\\")

    val conf = new SparkConf().setAppName("SparkProgramming").setMaster("local")
    val context = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(context)

    //test1 : show test data frame
    //testDataFrame(context, sqlContext)

    //test 2 : union of two data frames
    import sqlContext.implicits._ //To use toDF, I need to import this

    val dpt1 = Seq(departmentWithEmployees1, departmentWithEmployees5).toDF
    val dpt2 = Seq(departmentWithEmployees3, departmentWithEmployees4).toDF
    unionOfDataFrames(dpt1, dpt2)
  }

  def testDataFrame(sc : SparkContext, sqlContext : SQLContext): Unit = {
    import sqlContext.implicits._ //To use toDF, I need to import this

    val departmentsWithEmployeesSeq1 = Seq(departmentWithEmployees1, departmentWithEmployees2, departmentWithEmployees5)
    val df1 = departmentsWithEmployeesSeq1.toDF()

    df1.show()

    val departmentsWithEmployeesSeq2 = Seq(departmentWithEmployees3, departmentWithEmployees4)
    val df2 = departmentsWithEmployeesSeq2.toDF()
    df2.show()
  }

  def unionOfDataFrames(df1:DataFrame, df2:DataFrame): Unit = {
    val unionDF = df1.unionAll(df2)
    unionDF.show()

    //write the result to parquet
    unionDF.write.parquet("union_df.parquet")
  }

  // Create the case classes for our domain
  case class Department(id: String, name: String)
  case class Employee(firstName: String, lastName: String, email: String, salary: Int)
  case class DepartmentWithEmployees(department: Department, employees: Seq[Employee])

  // Create the Departments
  val department1 = new Department("123456", "Computer Science")
  val department2 = new Department("789012", "Mechanical Engineering")
  val department3 = new Department("345678", "Theater and Drama")
  val department4 = new Department("901234", "Indoor Recreation")
  val department5 = new Department("197502", "Dream Department")

  // Create the Employees
  val employee1 = new Employee("michael", "armbrust", "no-reply@berkeley.edu", 100000)
  val employee2 = new Employee("xiangrui", "meng", "no-reply@stanford.edu", 120000)
  val employee3 = new Employee("matei", null, "no-reply@waterloo.edu", 140000)
  val employee4 = new Employee(null, "wendell", "no-reply@princeton.edu", 160000)
  val employee5 = new Employee("Soojung", "Hong", "soojunghong@myfantasticlife.com", 200000)

  // Create the DepartmentWithEmployees instances from Departments and Employees
  val departmentWithEmployees1 = new DepartmentWithEmployees(department1, Seq(employee1, employee2))
  val departmentWithEmployees2 = new DepartmentWithEmployees(department2, Seq(employee3, employee4))
  val departmentWithEmployees3 = new DepartmentWithEmployees(department3, Seq(employee1, employee4))
  val departmentWithEmployees4 = new DepartmentWithEmployees(department4, Seq(employee2, employee3))
  val departmentWithEmployees5 = new DepartmentWithEmployees(department5, Seq(employee5))

}
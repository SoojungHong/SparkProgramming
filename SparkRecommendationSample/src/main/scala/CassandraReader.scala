/**
  * Created by a613274 on 27.11.2017.
  */

import org.apache.spark.sql.{DataFrame, SparkSession}


class CassandraReader(spark: SparkSession) {
  /**
    *
    * Wrapper method to read from Cassandra.
    *
    * @param keyspace
    * @param table
    * @return
    */
  def readTable(keyspace: String, table: String): DataFrame = {
    spark
      .read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> table, "keyspace" -> keyspace))
      .load()
  }
}

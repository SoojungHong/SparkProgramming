import org.apache.spark.sql.{DataFrame, SaveMode}

/**
  * Created by a613274 on 29.11.2017.
  */
object CassandraImplicit {

  implicit class cassandraWrite(df: DataFrame) {

    def writeToCassandra(keyspace: String, table: String): Unit = {
      df.write
        .format("org.apache.spark.sql.cassandra")
        .mode(SaveMode.Append)
        .options(Map("table" -> table, "keyspace" -> keyspace))
        .save()
    }

  }

}

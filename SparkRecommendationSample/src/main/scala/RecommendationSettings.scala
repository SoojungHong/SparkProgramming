/**
  * Created by a613274 on 27.11.2017.
  */

class RecommendationSettings extends Settings {
  val productsKeyspace: String = "product_keyspace" //config.getString("input.published-articles.cassandra.keyspace")
  val productsTable: String = "product_table" //config.getString("input.published-articles.cassandra.table")
 }

package com.exercise

import org.apache.spark.sql.DataFrame
import com.exercise.Setting._
import org.apache.spark.sql.functions._
import scala.reflect.internal.util.TableDef.Column

/**
  * Created by a613274 on 31.03.2017.
  */
object AnalyseParquet {

  implicit class AnalysisOperations(df : DataFrame) {

    def countColumnNum : Long = {
      df.count()
    }

    def showChannelAndBrowser(): DataFrame = {
      df.filter(df.col("channel") !== "prod-nzz")
        .select("browser")
    }

    //todo : filter().groupBy().aggregate().withColumn()
    def groupByAndAgg() = {
      df.filter(df.col("channel") !== "prod-mg")
        .groupBy("domain")
        .agg(max("color"))
    }
  }

}

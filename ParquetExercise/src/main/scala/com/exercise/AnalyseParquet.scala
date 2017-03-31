package com.exercise

import org.apache.spark.sql.DataFrame
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

  }


}

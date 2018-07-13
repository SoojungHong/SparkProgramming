package com.exercise.caseclass

/**
  * Created by a613274 on 10.05.2017.
  */
class ImplicitExercise {

}



/* NZZ Example

object CategoryScore {

  /**
    * Contains implicit method for category score calculations.
    * @param df Data frame
    */
  implicit class CategoryScoreOperations(df: DataFrame) {

    /**
      * Calculates category score as fraction of summary category visit count in a given period.
      *
      * Various period selection strategy can be specified.
      * Usage example:
      * {{{
      *   someDataFrame
      *    .categoryScore(new OverallCategoryScore())
      *    .write.save("some destination")
      * }}}
      *
      * @see [[ch.nzz.dnidatalayer.spark.categoryscore.strategy.CategoryScoreStrategy]]
      *
      * @param strategy Calculation strategy (mostly period selection)
      * @return Category score data frame
      */
    def categoryScore(implicit strategy: CategoryScoreStrategy): DataFrame = {


*/
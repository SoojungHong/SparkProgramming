package com.exercise.caseclass

import com.exercise.caseclass.CaseExercise.{SMS, VoiceRecording}

/**
  * Created by a613274 on 02.05.2017.
  */
object ScalaTest {
  def doScalaOperations(): Unit = {
    CaseExercise.testCase

    val someSms = SMS("12345", "Are you there?")
    val someVoiceRecording = VoiceRecording("Peter", "voicerecording.org/id/123")
    println(CaseExercise.showNotification(someSms))

  }

  def main(args : Array[String]) = {
    doScalaOperations()
  }

}

package com.exercise.caseclass

import com.exercise.caseclass.CaseExercise.Email
import com.exercise.caseclass.Notification
import org.apache.spark.sql.DataFrame

/**
  * Created by a613274 on 02.05.2017.
  */
object CaseExercise {

    def testCase: Unit = {
      val emailFromJohn = Email("john.doe@mail.com", "Greetings From John!", "Hello World!")

      val title = emailFromJohn.title
      println(title)
    }

    //With case classes, you can utilize pattern matching to work with your data.
    //Here’s a function that prints out different messages depending on what type of Notification is received:
  def showNotification(notification: Notification): String = {
    notification match {
      case Email(email, title, _) =>
        "You got an email from " + email + " with title: " + title
      case SMS(number, message) =>
        "You got an SMS from " + number + "! Message: " + message
      case VoiceRecording(name, link) =>
        "you received a Voice Recording from " + name + "! Click the link to hear it: " + link
    }
  }


    case class Email(sourceEmail: String, title: String, body: String) extends Notification
    case class SMS(sourceNumber: String, message: String) extends Notification
    case class VoiceRecording(contactName: String, link: String) extends Notification

}

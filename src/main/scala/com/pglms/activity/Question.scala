package com.pglms
package activity

case class Question(
  caseSensitive: String,
  text: Option[String],
  answer: Seq[Answer]
)

object Question {
  def fromElem(el: scala.xml.Node): Seq[Question] = {
    for {
      question <- (el \ "question")
    } yield Question(
      caseSensitive = question \@ "case_sensitive",
      text = (question \ "qtext").headOption.map(_.text),
      answer = Answer.fromElem(question)
    )
  }
}

package com.pglms
package activity

/**
 * Question
 * {{{
 * <question case_sensitive="No">
 *   <qtext>Hello</qtext>
 *   <answer>
 *     ...
 *   </answer>
 * </question>
 * }}}
 */
case class Question(
  caseSensitive: String,
  text: Option[String],
  answers: Seq[Answer]
)

object Question {
  def fromElem(el: scala.xml.Node): Seq[Question] = {
    for {
      question <- (el \ "question")
    } yield Question(
      caseSensitive = question \@ "case_sensitive",
      text          = (question \ "qtext").headOption.map(_.text),
      answers       = Answer.fromElem(question)
    )
  }
}

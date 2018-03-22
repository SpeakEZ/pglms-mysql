package com.pglms
package activity

/**
 * Answer
 * {{{
 * <answer>
 *   <atext>Hola</atext>
 * </answer>
 * }}}
 */
case class Answer(
  correct: String,
  audio: String,
  text: Option[String]
)

object Answer {
  def fromElem(el: scala.xml.Node): Seq[Answer] = {
    for {
      answer <- (el \ "answer")
    } yield {
      Answer(
        correct = answer \@ "correct",
        audio  = answer \@ "audio",
        text   = (answer \ "atext").headOption.map(_.text)
      )
    }
  }
}

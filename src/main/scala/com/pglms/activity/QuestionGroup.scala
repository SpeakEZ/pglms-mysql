package com.pglms
package activity

/**
 * Question group
 * {{{
 * <group/>
 * }}}
 */
case class QuestionGroup(
  questions: Seq[Question]
)

object QuestionGroup {
  def fromElem(el: scala.xml.Elem): Seq[QuestionGroup] = {
    for {
      group <- (el \ "group")
    } yield QuestionGroup(
      questions = Question.fromElem(group)
    )
  }
}

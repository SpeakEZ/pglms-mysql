package com.pglms
package activity

case class Drag(
  version: String,
  id: String,
  brand: String,
  course: String,
  title: String,
  graded: String,
  introduction: Option[Introduction],
  instructions: Option[Instructions],
  groups: Seq[QuestionGroup],
)

object Drag {

  def fromElem(el: scala.xml.Elem): Option[Drag] = {
    for {
      data <- Some(el) if el.label == "data" // (el \ "data").headOption
    } yield Drag(
      version       = data \@ "version",
      id            = data \@ "id",
      brand         = data \@ "brand",
      course        = data \@ "course",
      title         = data \@ "title",
      graded        = data \@ "graded",
      introduction  = Introduction.fromElem(data),
      instructions  = Instructions.fromElem(data),
      groups        = QuestionGroup.fromElem(data)
    )
  }

  def fromFile(file: String): Option[Drag] = {
    fromElem(scala.xml.XML.loadFile(file))
  }
}

case class Introduction(
  text: String
)

object Introduction {
  def fromElem(el: scala.xml.Elem): Option[Introduction] = {
    for {
      introduction <- (el \ "introduction").headOption
    } yield Introduction(
      text = introduction.text
    )
  }
}

case class Instructions(
  auto: String,
  text: String
)

object Instructions {
  def fromElem(el: scala.xml.Elem): Option[Instructions] = {
    for {
      instructions <- (el \ "instructions").headOption
    } yield Instructions(
      auto = instructions \@ "auto",
      text = instructions.text
    )
  }
}

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

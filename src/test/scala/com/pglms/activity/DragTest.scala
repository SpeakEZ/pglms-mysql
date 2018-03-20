package com.pglms
package activity

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.Inside
import org.scalatest.Inspectors

class DragTest
    extends FlatSpec
    with Matchers
    with Inside
    with Inspectors {

  "Drag.fromElem" should
  "return Drag" in {

    val xml: scala.xml.Elem =
      <data version="1.0" id="123" brand="Power-Glide" course="Spanish" title="Greetings" graded="No">
        <introduction/>
        <instructions auto="No">Do the activity.</instructions>
        <group/>
      </data>

    val drag = Drag.fromElem(xml)

    val expected = Drag(
      version       = "1.0",
      id            = "123",
      brand         = "Power-Glide",
      course        = "Spanish",
      title         = "Greetings",
      graded        = "No",
      introduction  = Some(Introduction("")),
      instructions  = Some(Instructions("No", "Do the activity.")),
      groups        = Seq(QuestionGroup(Seq.empty[Question]))
    )

    drag should be (Some(expected))
  }

  "Drag.fromFile" should
  "return Drag" in {

    val xmlFile = getClass.getClassLoader.getResource("activity/drag.xml").getFile

    val dragMaybe = Drag.fromFile(xmlFile)

    inside (dragMaybe) {
      case Some(drag) => {

        drag shouldBe a [Drag]

        inside (drag.introduction) {
          case Some(introduction) => {
            introduction.text should be ("")
          }
        }

        inside (drag.instructions) {
          case Some(instructions) => {
            instructions.auto should be ("No")
            instructions.text should startWith ("In this activity,")
          }
        }

        forAll(drag.groups) { group =>

          group shouldBe a [QuestionGroup]

          forAll(group.questions) { question =>

            question shouldBe a [Question]

            forAll (question.answer) { answer =>
              answer.correct should not be ("")
              answer.audio should not be ("")
              answer.text should not be ("")
            }
            question.caseSensitive should be ("No")
            question.text should not be ("")
          }
        }
      }
    }
  }
}

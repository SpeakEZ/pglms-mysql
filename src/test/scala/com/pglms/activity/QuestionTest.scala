package com.pglms
package activity

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.Inside
import org.scalatest.Inspectors

class QuestionTest
    extends FlatSpec
    with Matchers
    with Inside
    with Inspectors {

  "Question.fromElem" should
  "return Question" in {

    val xml: scala.xml.Elem =
      <group>
        <question case_sensitive="No">
          <qtext>Hello</qtext>
          <answer>
            <atext>Hola</atext>
          </answer>
        </question>
      </group>

    val drag = Question.fromElem(xml)

    val expected = Question(
      caseSensitive = "No",
      text          = Some("Hello"),
      answers        = Seq(
        Answer(
          correct = "",
          audio   = "",
          text    = Some("Hola")
        )
      )
    )

    drag should be (Seq(expected))
  }
}

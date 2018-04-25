package com.pglms
package activity

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.Inside
import org.scalatest.Inspectors

class StoryTest
    extends FlatSpec
    with Matchers
    with Inside
    with Inspectors {

  "Story.fromElem" should
  "return Story" in {

    val xml: scala.xml.Elem =
      <data>
        <image/>
        <audio/>
        <speakers>
          <speaker num="1">Person A</speaker>
        </speakers>
        <lines>
          <line speaker="1" cue="">Thank you.</line>
        </lines>
      </data>

    val story = Story.fromElem(xml)

    val expected = Story(
      audio  = Some(Audio("")),
      image  = Some(Image("")),
      speakers = Some(
        Speakers(
          speakers = Seq(
            Speaker(
              num     = "1",
              speaker = "Person A"
            )
          )
        )
      ),
      lines = Some(
        Lines(
          lines = Seq(
            Line(
              speaker = "1",
              cue     = "",
              line    = "Thank you."
            )
          )
        )
      )
    )
    story should be (Some(expected))
  }

  "Story.fromFile" should
  "return Story" in {

    val xmlFile = getClass.getClassLoader.getResource("activity/story.xml").getFile

    val storyMaybe = Story.fromFile(xmlFile)

    inside (storyMaybe) {
      case Some(story) => {

        story shouldBe a [Story]

        inside (story.image) {
          case Some(image) => {
            image.url should not be ("")
          }
        }

        inside (story.audio) {
          case Some(audio) => {
            audio.url should not be ("")
          }
        }

        inside (story.speakers) {
          case Some(speakers) => {
            speakers shouldBe a [Speakers]

            forAll(speakers.speakers) { speaker =>

              speaker shouldBe a [Speaker]

              speaker.num should not be ("")
              speaker.speaker should not be ("")
            }
          }
        }

        inside (story.lines) {
          case Some(lines) => {

            lines shouldBe a [Lines]

            forAll(lines.lines) { line =>

              line shouldBe a [Line]

              line.speaker should not be ("")
              line.cue should be ("")
              line.line should not be ("")
            }
          }
        }
      }
    }
  }
}

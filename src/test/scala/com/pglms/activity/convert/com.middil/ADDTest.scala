package com.pglms
package activity
package convert.com.middil

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ADDTest
    extends FlatSpec
    with Matchers {

  "convert.com.middil.ADD.fromDrag" should
  "return ADD" in {

    val drag = Drag(
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

    val result = ADD.fromDrag(drag)

    val expected = com.middil.activity.xml.ADD(
      author       = "SpeakEZ 2.5",
      saved        = "Wed Mar 14 16:23:00 GMT-0400 2018",
      randomize    = "true",
      images       = Seq.empty[com.middil.activity.xml.Image],
      timeout      = Some(
        com.middil.activity.xml.Timeout(
          show = "false",
          duration = Some(0L)
        )
      ),
      timeoutIndex = Some(
        com.middil.activity.xml.TimeoutIndex(
          show = "true",
          index = Some(0L)
        )
      ),
      timer        = None,
      scored       = None,
      attempts     = Some(3L),
      messages     = Seq.empty[com.middil.activity.xml.Message],
      groups       = Seq.empty[com.middil.activity.xml.Group],
      elementCount = Some(
        com.middil.activity.xml.ElementCount(
          targetCount = Some(0L),
          dragCount   = Some(0L)
        )
      ),
      draggables   = Seq.empty[com.middil.activity.xml.Draggable],
      elements     = Seq.empty[com.middil.activity.xml.Element],
      containers   = Seq.empty[com.middil.activity.xml.Container],
      hotspotLists = Seq.empty[com.middil.activity.xml.HotspotList],
      hotspots     = Seq.empty[com.middil.activity.xml.Hotspot],
      weight       = Some(
        com.middil.activity.xml.Weight(
          total = Some(
            com.middil.activity.xml.Total(
              weight = Some(0L)
            )
          )
        )
      )
    )

    result should be (expected)
  }

  "convert.com.middil.ADD.fromDrag" should
  "return XML" in {

    val dragFile = getClass.getClassLoader.getResource("activity/drag.xml").getFile

    val dragMaybe = Drag.fromFile(dragFile)

    val add = ADD.fromDrag(dragMaybe.get)

    val xml = com.middil.activity.xml.ADD.toElem(add)

    val addFile = getClass.getClassLoader.getResource("activity/middil.com/add.xml").getFile

    val pp = new scala.xml.PrettyPrinter(1000, 2)

    // val expected = scala.xml.XML.loadFile(ADD.toElem(ADD.fromFile(addFile)))
    // pp.format(xml) should be (pp.format(expected))

    val expected = scala.io.Source.fromFile(addFile).mkString

    pp.format(xml) should be (expected)
  }
}

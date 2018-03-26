package com.middil
package activity
package xml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ADDTest
    extends FlatSpec
    with Matchers {

  "com.middil.activity.xml.ADD.toElem" should
  "return XML" in {

    val add = ADD(
      author       = "SpeakEZ 2.5",
      saved        = "Wed Mar 14 16:23:00 GMT-0400 2018",
      randomize    = "true",
      images       = Seq.empty[Image],
      timeout      = None,
      timeoutIndex = None,
      timer        = None,
      scored       = None,
      attempts     = None,
      messages     = Seq.empty[Message],
      groups       = Seq.empty[Group],
      elementCount = None,
      draggables   = Seq.empty[Draggable],
      elements     = Seq.empty[Element],
      containers   = Seq.empty[Container],
      hotspotLists = Seq.empty[HotspotList],
      hotspots     = Seq.empty[Hotspot],
      weight       = None
    )

    val xml = ADD.toElem(add)

    val expected: scala.xml.Elem =
      <data type="ADD" 
            author="SpeakEZ 2.5" 
            saved="Wed Mar 14 16:23:00 GMT-0400 2018" 
            randomize="true">
        <image/>
        <timer>down</timer>
        <scored>true</scored>
        <attempts>0</attempts>
        <message>
          <text/>
          <audio/>
        </message>
        <group/>
      </data>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

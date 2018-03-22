package com.middil
package activity
package xml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class HotspotTest
    extends FlatSpec
    with Matchers {

  "Hotspot.toElem" should
  "return XML" in {

    val hotspot = Hotspot(
      hotspotType         =  "rectangle",
      itemName            =  "hotspot1",
      width               =  250,
      height              =  100,
      x                   =  456,
      y                   =  28,
      rotation            =  0,
      dropOpacity         =  1,
      isDragDropContainer =  "true",
      associations        = Some(Associations("draggable1"))
    )
    val xml = Hotspot.toElem(hotspot)

    val expected: scala.xml.Elem =
      <hotspot type="rectangle"
               itemName="hotspot1"
               width="250"
               height="100"
               x="456"
               y="28"
               rotation="0"
               dropOpacity="1"
               isDragDropContainer="true">
        <associations>draggable1</associations>
      </hotspot>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

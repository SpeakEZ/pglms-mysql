package com.middil
package activity
package xml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class HMTTest
    extends FlatSpec
    with Matchers {

  "com.middil.activity.xml.HMT.toElem" should
  "return XML" in {

    val hmt = HMT(
      author       = "SpeakEZ 2.5",
      saved        = "Thu Mar 29 16:08:04 GMT-0400 2018",
      version      = "2.12222011",
      activity     = None
    )

    val xml = HMT.toElem(hmt)

    val expected: scala.xml.Elem =
      <data type="HMT" 
            author="SpeakEZ 2.5" 
            saved="Thu Mar 29 16:08:04 GMT-0400 2018" 
            version="2.12222011">
      </data>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

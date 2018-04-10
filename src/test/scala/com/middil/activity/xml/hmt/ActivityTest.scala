package com.middil
package activity
package xml
package hmt

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ActivityTest
    extends FlatSpec
    with Matchers {

  "Activity.toElem" should
  "return XML" in {

    val activity = Activity(
      weight       = None,
      metaData     = None,
      containers   = Seq.empty[HMT.containers]
    )

    val xml = Activity.toElem(activity)

    val expected: scala.xml.Elem =
      <activity>      
      </activity>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

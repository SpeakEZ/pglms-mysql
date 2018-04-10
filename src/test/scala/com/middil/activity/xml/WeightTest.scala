package com.middil
package activity
package xml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class WeightTest
    extends FlatSpec
    with Matchers {

  "Weight.toElem" should
  "return XML" in {

    val weight = Weight(
      total = Some(
        Total(
          weight = Some(0L)
        )
      ),
      extra = Some(
        Extra(
          amount = Some(0L)
        )
      )
    )

    val xml = Weight.toElem(weight)

    val expected: scala.xml.Elem =
      <weight>
        <total>0</total>
        <extra>0</extra>
      </weight>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

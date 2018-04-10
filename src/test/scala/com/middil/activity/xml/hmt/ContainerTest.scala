package com.middil.activity.xml.hmt

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ContainerTest
    extends FlatSpec
    with Matchers {

  "Container.toElem" should
  "return XML" in {

    val container = Container(
      containerType   = "free_text",
      x               = "25",
      y               = "50",
      width           = "100",
      height          = "200",
      borderStyle     = "none",
      borderColor     = "0",
      backgroundColor = "0",
      backgroundAlpha = "0",
      borderPadding   = "0",
      borderThickness = "0",
      borderRadius    = "0",
      rotation        = "0",
      elements        = Seq.empty[ContainerElements]
    )

    val xml = Container.toElem(container)

    val expected: scala.xml.Elem =
      <container type="free_text" x="25" y="50" width="100" height="200" rotation="0" borderStyle="none" borderColor="0" backgroundColor="0" backgroundAlpha="0" borderPadding="0" borderThickness="0" borderRadius="0">
      </container>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

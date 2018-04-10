package com.middil.activity.xml.hmt

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ContainerElementTest
    extends FlatSpec
    with Matchers {

  "ContainerElement.toElem" should
  "return XML" in {

    val containerElement = ContainerElement(
      textArea = Some(
        ElementTextArea(
          editable              = "false",
          toolBar               = "false",
          lineHeight            = 17,
          weight                = 0,
          verticalTextAlignment = "alignTop",
          text                  = """|<HTML>
                                     |<BODY>
                                     |<P ALIGN="left">Hello</P>
                                     |</BODY>
                                     |</HTML>
                                     |""".stripMargin.lines.mkString
        )
      ),
      elementType = None,
      text = None,
      image = None,
      media = None
    )

    val xml = ContainerElement.toElem(containerElement)

    val expected: scala.xml.Elem =
      <element>
        <textArea editable="false" toolBar="false" lineHeight="17" weight="0" verticalTextAlignment="alignTop">
          &lt;HTML&gt;&lt;BODY&gt;&lt;P ALIGN=&quot;left&quot;&gt;Hello&lt;/P&gt;&lt;/BODY&gt;&lt;/HTML&gt;
        </textArea>
      </element>

    val pp = new scala.xml.PrettyPrinter(1000, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

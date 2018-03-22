package com.middil
package activity
package xml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ElementTest
    extends FlatSpec
    with Matchers {

  "Element.toElem" should
  "return XML" in {

    val element = Element(
      hideText              = "false",
      hideBorder            = "false",
      elementType           = "Draggable Element",
      itemName              = "draggable1",
      mediaType             = "Text",
      verticalTextAlignment = "alignMiddle",
      insideTextHTML        =
        """|<HTML>
           |<BODY>
           |<P ALIGN="center">
           |<FONT FACE="Arial" SIZE="12" COLOR="#000000" LETTERSPACING="0" KERNING="1">
           |<FONT SIZE="18">Hola</FONT>
           |</FONT>
           |</P>
           |</BODY>
           |</HTML>""".stripMargin.lines.mkString,
      roundedEdges          = "false",
      scaleInTarget         = "false",
      bkColor               = "16777215",
      width                 = 250,
      height                = 100,
      x                     = 51,
      y                     = 299,
      rotation              = 0,
      dropOpacity           = BigDecimal("0.8"),
      autoPlay              = "false",
      mute                  = "false",
      backgroundOpacity     = BigDecimal("0.75"),
      maintainAspectRatio   = "true",
      notTarget             = "true",
      media                 = Some(
        ElementMedia(
          mediaThumbnail = ""
        )
      ),
      audio                 = Some(
        ElementAudio(
          iconColor = "229740"
        )
      )
    )

    val xml = Element.toElem(element)

    val expected: scala.xml.Elem =
      <element hideText="false"
               hideBorder="false"
               type="Draggable Element"
               itemName="draggable1"
               mediaType="Text"
               verticalTextAlignment="alignMiddle"
               insideTextHTML="&lt;HTML>&lt;BODY>&lt;P ALIGN=&quot;center&quot;>&lt;FONT FACE=&quot;Arial&quot; SIZE=&quot;12&quot; COLOR=&quot;#000000&quot; LETTERSPACING=&quot;0&quot; KERNING=&quot;1&quot;>&lt;FONT SIZE=&quot;18&quot;>Hola&lt;/FONT>&lt;/FONT>&lt;/P>&lt;/BODY>&lt;/HTML>"
               roundedEdges="false"
               scaleInTarget="false"
               bkColor="16777215"
               width="250"
               height="100"
               x="51"
               y="299"
               rotation="0"
               dropOpacity="0.8"
               autoPlay="false"
               mute="false"
               backgroundOpacity="0.75"
               maintainAspectRatio="true"
               notTarget="true">
        <media mediaThumbnail=""/>
        <audio iconColor="229740"/>
      </element>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

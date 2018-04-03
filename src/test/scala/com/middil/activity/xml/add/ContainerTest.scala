package com.middil
package activity
package xml
package add

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ContainerTest
    extends FlatSpec
    with Matchers {

  "Container.toElem" should
  "return XML" in {

    val container = Container(
      x               = "288",
      y               = "44",
      width           = "150",
      height          = "54.5",
      borderStyle     = "none",
      borderColor     = "0",
      backgroundColor = "0",
      backgroundAlpha = "0",
      borderPadding   = "0",
      borderThickness = "0",
      borderRadius    = "0",
      rotation        = "0",
      containerType   = "interactive_text",
      label           = "",
      randomize       = "false",
      weight          = "0",
      elements        = Seq(
        ContainerElements(
          fontSize       = "14",
          fontWeight     = "normal",
          fontFamily     = "Arial",
          fontPosture    = "normal",
          textDecoration = "none",
          lineHeight     = "0",
          fontColor      = "0",
          rearrange      = "false",
          elements       = Seq(
            ContainerElement(
              fontSize       = "14",
              fontWeight     = "normal",
              fontFamily     = "Arial",
              fontPosture    = "normal",
              textDecoration = "undefined",
              lineHeight     = "0",
              fontColor      = "0",
              elementType    = Some(
                ElementType(
                  toolTip     = "",
                  elementType = "text"
                )
              ),
              elementText    = Some(
                ElementText(
                  elementText = "Hello"
                )
              )
            )
          )
        )
      )
    )

    val xml = Container.toElem(container)

    val expected: scala.xml.Elem =
      <container x="288"
                 y="44"
                 width="150"
                 height="54.5"
                 borderStyle="none"
                 borderColor="0"
                 backgroundColor="0"
                 backgroundAlpha="0"
                 borderPadding="0"
                 borderThickness="0"
                 borderRadius="0"
                 rotation="0"
                 type="interactive_text"
                 label=""
                 randomize="false"
                 weight="0">
        <elements fontSize="14"
                  fontWeight="normal"
                  fontFamily="Arial"
                  fontPosture="normal"
                  textDecoration="none"
                  lineHeight="0"
                  fontColor="0"
                  rearrange="false">
          <element fontSize="14"
                   fontWeight="normal"
                   fontFamily="Arial"
                   fontPosture="normal"
                   textDecoration="undefined"
                   lineHeight="0"
                   fontColor="0">
            <type toolTip="">text</type>
            <text>Hello</text>
          </element>
        </elements>
      </container>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

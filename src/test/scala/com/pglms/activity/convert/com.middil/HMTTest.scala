package com.pglms
package activity
package convert.com.middil

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class HMTTest
    extends FlatSpec
    with Matchers {

  "convert.com.middil.HMT.fromCommentary" should
  "return HMT" in {

    val str =
      """|<p>Hello</p>
         |<ul>
         |<li>Vocabulary</li>
         |</ul>
         |""".stripMargin.lines.mkString

    val Some(commentary) = Commentary.fromString(str)

    val result = HMT.fromCommentary(commentary)

    val expected = com.middil.activity.xml.HMT(
      author   = "SpeakEZ 2.5",
      saved    = "Thu Mar 29 16:08:04 GMT-0400 2018",
      version  = "2.12222011",
      activity = Some(
        com.middil.activity.xml.Activity(
          weight    = Some(
            com.middil.activity.xml.Weight(
              total = Some(
                com.middil.activity.xml.Total(
                  weight = Some(0L)
                )
              ),
              extra = Some(
                com.middil.activity.xml.Extra(
                  amount = Some(0L)
                )
              )
            )
          ),
          metaData  = Some(
            com.middil.activity.xml.MetaData(
              activityType = "HMT"
            )
          ),
          containers = Seq(
            com.middil.activity.xml.hmt.Containers(
              containers = Seq(
                com.middil.activity.xml.hmt.Container(
                  x               = "213",
                  y               = "9.9",
                  width           = "575",
                  height          = "466",
                  borderStyle     = "none",
                  borderColor     = "0",
                  backgroundColor = "0",
                  backgroundAlpha = "0",
                  borderPadding   = "0",
                  borderThickness = "0",
                  borderRadius    = "0",
                  rotation        = "0",
                  containerType   = "free_text",
                  elements        = Seq(
                    com.middil.activity.xml.hmt.ContainerElements(
                      elements       = Seq(
                        com.middil.activity.xml.hmt.ContainerElement(
                          elementType = None,
                          text        = None,
                          textArea    = Some(
                            com.middil.activity.xml.hmt.ElementTextArea(
                              editable              = "false",
                              toolBar               = "false",
                              lineHeight            = 17,
                              weight                = 0,
                              verticalTextAlignment = "alignTop",
                              text                  =
                                """|<HTML>
                                   |<BODY>
                                   |<P><FONT FACE="Arial" SIZE="12" COLOR="#2d2d1e" LETTERSPACING="0" KERNING="1">Hello</FONT></P>
                                   |<P></P>
                                   |<UL>
                                   |<LI><FONT FACE="Arial" SIZE="12" COLOR="#2d2d1e" LETTERSPACING="0" KERNING="1">Vocabulary</FONT></LI>
                                   |</UL>
                                   |</BODY>
                                   |</HTML>""".stripMargin.lines.mkString
                            )
                          ),
                          image       = None,
                          media       = None
                        )
                      )
                    )
                  )
                ),
                com.middil.activity.xml.hmt.Container(
                  x               = "35",
                  y               = "42",
                  width           = "140",
                  height          = "400",
                  borderStyle     = "none",
                  borderColor     = "0",
                  backgroundColor = "0",
                  backgroundAlpha = "0",
                  borderPadding   = "0",
                  borderThickness = "0",
                  borderRadius    = "0",
                  rotation        = "0",
                  containerType   = "media",
                  elements        = Seq(
                    com.middil.activity.xml.hmt.ContainerElements(
                      elements       = Seq(
                        com.middil.activity.xml.hmt.ContainerElement(
                          elementType = Some(
                            com.middil.activity.xml.hmt.ElementType(
                              toolTip     = "",
                              elementType = "image"
                            )
                          ),
                          textArea    = None,
                          text        = None,
                          image       = Some(
                            com.middil.activity.xml.hmt.ElementImage(
                              autoplay       = "false",
                              maintainAspect = "false",
                              mute           = "false",
                              url            = Some(
                                com.middil.activity.xml.URL(
                                  url = "https://d3s7d0hr4rzqgi.cloudfront.net/image/170deec3bb9d804674c98e52a122784d491cfc96.png"
                                )
                              ),
                              thumbnail      = Some(
                                com.middil.activity.xml.Thumbnail(
                                )
                              ),
                              audio          = Some(
                                com.middil.activity.xml.ElementAudio(
                                  iconColor = "",
                                  source    = Some(
                                    com.middil.activity.xml.AudioSource(
                                    )
                                  )
                                )
                              )
                            )
                          ),
                          media       = None
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )
    )
    result should be (expected)
  }

  "convert.com.middil.HMT.fromCommentary" should
  "return XML" in {

    val commentaryFile = getClass.getClassLoader.getResource("activity/commentary.html").getFile

    val commentaryMaybe = Commentary.fromFile(commentaryFile)

    val hmt = HMT.fromCommentary(commentaryMaybe.get)

    val xml = com.middil.activity.xml.HMT.toElem(hmt)

    val hmtFile = getClass.getClassLoader.getResource("activity/middil.com/hmt.xml").getFile

    val pp = new scala.xml.PrettyPrinter(1000, 2)

    // val expected = scala.xml.XML.loadFile(HMT.toElem(HMT.fromFile(hmtFile)))
    // pp.format(xml) should be (pp.format(expected))

    val expected = scala.io.Source.fromFile(hmtFile).mkString.stripLineEnd

    pp.format(xml) should be (expected)
  }


  "convert.com.middil.HMT.makeHtmlString" should
  "return String" in {

    val html: scala.xml.Elem = 
      <html>
        <p>Hello</p>
        <ul>
          <li>Vocabulary</li>
        </ul>
      </html>

    val result = HMT.makeHtmlString(html.child)

    val expected =
      """|<HTML>
         |<BODY>
         |<P><FONT FACE="Arial" SIZE="12" COLOR="#2d2d1e" LETTERSPACING="0" KERNING="1">Hello</FONT></P>
         |<P></P>
         |<UL>
         |<LI><FONT FACE="Arial" SIZE="12" COLOR="#2d2d1e" LETTERSPACING="0" KERNING="1">Vocabulary</FONT></LI>
         |</UL>
         |</BODY>
         |</HTML>
         |""".stripMargin.lines.mkString

    result should be (expected)
  }

  "convert.com.middil.HMT.uppercaseAttributes" should
  "return MetaData" in {

    val xml: scala.xml.Elem = <v w:x="a" y="b" z="c"/>

    val result = HMT.uppercaseAttributes(xml.attributes)

    val expected = <v W:X="a" Y="b" Z="c" />

    xml.copy(attributes = result) should be (expected)
  }
}

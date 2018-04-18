package com.pglms
package activity
package convert.com.middil

/**
 * Activity drag and drop converter.
 *
 * Convert from
 * [[com.pglms.activity.Commentary]]
 * to
 * [[com.middil.activity.xml.HMT]]
 */
object HMT {

  def fromCommentary(commentary: Commentary) = com.middil.activity.xml.HMT(
    author   = "SpeakEZ 2.5",
    saved    = "Thu Mar 29 16:08:04 GMT-0400 2018",
    version  = "2.12222011",
    activity = Some(
      com.middil.activity.xml.Activity(
        weight     = Some(
          com.middil.activity.xml.Weight(
            total  = Some(
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
        metaData   = Some(
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
                        textArea    = Some(
                          com.middil.activity.xml.hmt.ElementTextArea(
                            editable              = "false",
                            toolBar               = "false",
                            lineHeight            = 17,
                            weight                = 0,
                            verticalTextAlignment = "alignTop",
                            text                  = makeHtmlString(commentary.html)
                          )
                        ),
                        text        = None,
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

  def fontText(elem: scala.xml.Text): scala.xml.Elem = {
    <font face="Arial" size="12" color="#2d2d1e" letterspacing="0" kerning="1">{ elem }</font>
  }

  val fontRule = new scala.xml.transform.RewriteRule {
    override def transform(n: scala.xml.Node): Seq[scala.xml.Node] = {
      n match {
      case text: scala.xml.Text if !text.data.isEmpty =>
        fontText(text)
      case n => n
      }
    }
  }

  val paragraphRule = new scala.xml.transform.RewriteRule {
    override def transform(n: scala.xml.Node): Seq[scala.xml.Node] = {
      n match {
      case elem: scala.xml.Elem if elem.label == "p" =>
        elem ++ <p></p>
      case n => n
      }
    }
  }

  val spaceAtBeginningOfLines: scala.util.matching.Regex = "(\n[ \t]+)+".r

  val leadingWhitespaceRule = new scala.xml.transform.RewriteRule {
    override def transform(n: scala.xml.Node): Seq[scala.xml.Node] = {
      n match {
        case text: scala.xml.Text if text.data.contains("\n") => {
          scala.xml.Text(spaceAtBeginningOfLines.replaceFirstIn(text.data, ""))
        }
      case n => n
      }
    }
  }

  def uppercaseAttributes(attribs: scala.xml.MetaData): scala.xml.MetaData = {
    // FIXME: Done in reverse with right fold, because scala-xml
    // incorrectly iterates attributes in reverse order.
    attribs.foldRight(scala.xml.Null: scala.xml.MetaData) { (a, aa) =>
      a match {
        case a: scala.xml.PrefixedAttribute => {
          new scala.xml.PrefixedAttribute(
            a.pre.toUpperCase,
            a.key.toUpperCase,
            a.value,
            aa
          )
        }
        case a: scala.xml.UnprefixedAttribute => {
          new scala.xml.UnprefixedAttribute(
            a.key.toUpperCase,
            a.value,
            aa
          )
        }
        case scala.xml.Null => aa
      }
    }
  }

  val uppercaseRule = new scala.xml.transform.RewriteRule {
    override def transform(n: scala.xml.Node): Seq[scala.xml.Node] = n match {
      case elem: scala.xml.Elem =>
        elem.copy(
          label = elem.label.toUpperCase,
          attributes = uppercaseAttributes(elem.attributes)
        )
      case n => n
    }
  }

  val transformHTML = new scala.xml.transform.RuleTransformer(
    leadingWhitespaceRule,
    fontRule,
    paragraphRule,
    uppercaseRule
  )

  def htmlBodyWrap(html: scala.xml.NodeSeq): scala.xml.Elem = {
    <html/>.copy(child = <body/>.copy(child = html))
  }

  def makeHtmlString(html: scala.xml.NodeSeq): String = {
    transformHTML(
      // HACK: Fake a scala.xml.Node "root" element.
      <root/>.copy(child = htmlBodyWrap(html))
    ).child.mkString
  }
}

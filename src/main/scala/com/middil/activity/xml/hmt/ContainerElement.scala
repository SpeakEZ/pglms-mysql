package com.middil
package activity
package xml
package hmt

/**
 * Element
 * {{{
 * <element/>
 * }}}
 */
case class ContainerElement(
  elementType: Option[ElementType],
  textArea:    Option[ElementTextArea],
  text:        Option[ElementText],
  image:       Option[ElementImage],
  media:       Option[ElementMedia]
)

object ContainerElement {

  def toElem(containerElement: ContainerElement): scala.xml.Elem = {
    <element>
      { containerElement.textArea.map(
          ElementTextArea.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty) 
      }
      { containerElement.elementType.map(
          ElementType.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty) 
      }
      { containerElement.text.map(
          ElementText.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty) 
      }
      { containerElement.media.map(
          ElementMedia.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty) 
      }
      { containerElement.media.map(
          ElementMedia.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty) 
      }
    </element>
  }

  def toElem(containerElements: Seq[ContainerElement]): scala.xml.NodeSeq = {
    for {
      containerElement <- containerElements
    } yield {
      ContainerElement.toElem(containerElement)
    }
  }
}

/**
 * Type
 * {{{
 * <type/>
 * }}}
 */
case class ElementType(
  toolTip: String,
  elementType: String
)

object ElementType {
  def toElem(elementType: ElementType): scala.xml.Elem = {
    <type toolTip={elementType.toolTip}>{elementType.elementType}</type>
  }
}

/**
 * Text
 * {{{
 * <text/>
 * }}}
 */
case class ElementText(
  elementText: String
)

object ElementText {
  def toElem(elementText: ElementText): scala.xml.Elem = {
    <text>{elementText.elementText}</text>
  }
}

/**
 * Image
 * {{{
 * <image/>
 * }}}
 */
case class ElementImage(
  autoplay: String,
  maintainAspect: String,
  mute: String,
  url: Option[URL],
  thumbnail: Option[Thumbnail],
  audio: Option[ElementAudio]
)

object ElementImage {
  def toElem(elementImage: ElementImage): scala.xml.Elem = {
    <image autoplay={elementImage.autoplay} maintainAspect={elementImage.maintainAspect} mute={elementImage.mute}>
      { elementImage.url.map(
          URL.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty)
      }
      { elementImage.thumbnail.map(
          Thumbnail.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty)
      }
      { elementImage.audio.map(
          ElementAudio.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty)
      }
    </image>
  }
}

/**
 * Text area
 * {{{
 * <textArea/>
 * }}}
 */
case class ElementTextArea(
  editable: String,
  toolBar: String,
  lineHeight: Long,
  weight: Long,
  verticalTextAlignment: String,
  text: String
)

object ElementTextArea {
  def toElem(elementText: ElementTextArea): scala.xml.Elem = {
    <textArea editable={elementText.editable}
              toolBar={elementText.toolBar}
              lineHeight={elementText.lineHeight.toString}
              weight={elementText.weight.toString}
              verticalTextAlignment={elementText.verticalTextAlignment}>
      {elementText.text}
    </textArea>
  }
}

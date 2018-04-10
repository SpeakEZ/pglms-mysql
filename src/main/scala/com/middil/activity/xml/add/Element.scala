package com.middil
package activity
package xml
package add

/**
 * <element
 *   x="288"
 *   y="44"
 *   width="150"
 *   height="54.5"
 *   borderStyle="none"
 *   borderColor="0"
 *   backgroundColor="0"
 *   backgroundAlpha="0"
 *   borderPadding="0"
 *   borderThickness="0"
 *   borderRadius="0"
 *   rotation="0"
 *   type="interactive_text"
 *   label=""
 *   randomize="false"
 *   weight="0">...</element>
 */
case class Element(
  hideText: String,
  hideBorder: String,
  elementType: String,
  itemName: String,
  mediaType: String,
  verticalTextAlignment: String,
  insideTextHTML: String,
  roundedEdges: String,
  scaleInTarget: String,
  bkColor: String,
  width: Long,
  height: Long,
  x: Long,
  y: Long,
  rotation: Long,
  dropOpacity: BigDecimal,
  autoPlay: String,
  mute: String,
  backgroundOpacity: BigDecimal,
  maintainAspectRatio: String,
  notTarget: String,
  media: Option[ElementMedia],
  audio: Option[ElementAudio]
)

object Element {

  def toElem(element: Element): scala.xml.Elem = {
    <element hideText={element.hideText}
             hideBorder={element.hideBorder}
             type={element.elementType}
             itemName={element.itemName}
             mediaType={element.mediaType}
             verticalTextAlignment={element.verticalTextAlignment}
             insideTextHTML={element.insideTextHTML}
             roundedEdges={element.roundedEdges}
             scaleInTarget={element.scaleInTarget}
             bkColor={element.bkColor}
             width={element.width.toString}
             height={element.height.toString}
             x={element.x.toString}
             y={element.y.toString}
             rotation={element.rotation.toString}
             dropOpacity={element.dropOpacity.toString}
             autoPlay={element.autoPlay}
             mute={element.mute}
             backgroundOpacity={element.backgroundOpacity.toString}
             maintainAspectRatio={element.maintainAspectRatio}
             notTarget={element.notTarget}>
      { element.media.map(
          ElementMedia.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty)
      }
      { element.audio.map(
          ElementAudio.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty)
      }
    </element>
  }

  def toElem(elements: Seq[Element]): scala.xml.NodeSeq = {
    for {
      element <- elements
    } yield {
      Element.toElem(element)
    }
  }
}

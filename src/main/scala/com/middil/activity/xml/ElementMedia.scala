package com.middil
package activity
package xml

/**
 * Element media
 * {{{
 * <media mediaThumbnail=""/>
 * }}}
 */
case class ElementMedia(
  mediaThumbnail: String
)

object ElementMedia {

  def toElem(elementMedia: ElementMedia): scala.xml.Elem = {
    <media mediaThumbnail={elementMedia.mediaThumbnail}/>
  }
}

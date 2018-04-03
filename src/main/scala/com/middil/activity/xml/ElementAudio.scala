package com.middil
package activity
package xml

/**
 * Element audio
 * {{{
 * <audio iconColor="229740"/>
 * }}}
 */
case class ElementAudio(
  iconColor: String
)

object ElementAudio {

  def toElem(elementAudio: ElementAudio): scala.xml.Elem = {
    <audio iconColor={elementAudio.iconColor}/>
  }
}

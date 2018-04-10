package com.middil
package activity
package xml

/**
  * Thumbnail
  * {{{
  * <thumbnail/>
  * }}}
  */
case class Thumbnail(
)

object Thumbnail {
  def toElem(thumbnail: Thumbnail): scala.xml.Elem = {
    <thumbnail/>
  }
}

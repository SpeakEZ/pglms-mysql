package com.middil
package activity
package xml

/**
  * URL
  * {{{
  * <url>http://...</url>
  * }}}
  */
case class URL(
  url: String
)

object URL {
  def toElem(url: URL): scala.xml.Elem = {
    <url>{url.url}</url>
  }
}

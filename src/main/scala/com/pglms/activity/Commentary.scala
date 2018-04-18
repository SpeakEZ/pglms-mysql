package com.pglms
package activity

/**
 * Adviser HTML activity (commentary)
 * {{{
 * <p>&iexcl;Bienvenido a tu clase de espa&ntilde;ol! ...
 * </p>
 * }}}
 *
 * @see [[com.pglms.activity.convert.com.middil.HMT$]]
 */
case class Commentary(
  html: scala.xml.NodeSeq
)

object Commentary {

  def fromString(str: String): Option[Commentary] = {
    Some(
      Commentary(
        html = new scala.xml.parsing.XhtmlParser(
          scala.io.Source.fromString(str)
        ).initialize.document.children
      )
    )
  }

  def fromFile(file: String): Option[Commentary] = {
    Some(
      Commentary(
        html = new scala.xml.parsing.XhtmlParser(
          scala.io.Source.fromFile(file)
        ).initialize.document.children
      )
    )
  }
}

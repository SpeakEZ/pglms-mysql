package com.middil
package activity
package xml
package hmt

/**
 * Elements
 * {{{
 * <elements/>
 * }}}
 */
case class ContainerElements(
  elements: Seq[ContainerElement]
)

object ContainerElements {

  def toElem(containerElements: ContainerElements): scala.xml.Elem = {
    <elements>
      { ContainerElement.toElem(containerElements.elements) }
    </elements>
  }

  def toElem(containerElements: Seq[ContainerElements]): scala.xml.NodeSeq = {
    for {
      containerElement <- containerElements
    } yield {
      ContainerElements.toElem(containerElement)
    }
  }
}

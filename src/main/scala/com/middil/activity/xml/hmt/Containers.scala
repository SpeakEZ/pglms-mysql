package com.middil
package activity
package xml
package hmt

/**
  * Container
  * {{{
  * <containers>
  *   <container type="free_text" x="50" y="50" width="200" height="50">
  *     <elements>
  *       <element>
  *          ...
  *       </element>
  *     </elements>
  *   </container>
  * </containers>
  * }}}
  */
case class Containers(
  containers: Seq[Container]
)

object Containers {

  def toElem(containers: Containers): scala.xml.Elem = {
    <containers>
      { Container.toElem(containers.containers) }
    </containers>
  }

  def toElem(containers: Seq[Containers]): scala.xml.NodeSeq = {
    for {
      container <- containers
    } yield {
      Containers.toElem(container)
    }
  }
}

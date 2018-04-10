package com.middil
package activity
package xml
package hmt

/**
  * Container
  * {{{
  * <container x="50" y="50" width="200" height="50">
  *   <elements>
  *     <element>
  *        ...
  *     </element>
  *   </elements>
  * </container>
  * }}}
  */
case class Container(
  containerType: String,
  x: String,
  y: String,
  width: String,
  height: String,
  borderStyle: String,
  borderColor: String,
  backgroundColor: String,
  backgroundAlpha: String,
  borderPadding: String,
  borderThickness: String,
  borderRadius: String,
  rotation: String,
  elements: Seq[ContainerElements],
)

object Container {

  def toElem(container: Container): scala.xml.Elem = {
    <container type={container.containerType}
               x={container.x}
               y={container.y}
               width={container.width}
               height={container.height}
               rotation={container.rotation}
               borderStyle={container.borderStyle}
               borderColor={container.borderColor}
               backgroundColor={container.backgroundColor}
               backgroundAlpha={container.backgroundAlpha}
               borderPadding={container.borderPadding}
               borderThickness={container.borderThickness}
               borderRadius={container.borderRadius}>
      { ContainerElements.toElem(container.elements) }
    </container>
  }

  def toElem(containers: Seq[Container]): scala.xml.NodeSeq = {
    for {
      container <- containers
    } yield {
      Container.toElem(container)
    }
  }
}

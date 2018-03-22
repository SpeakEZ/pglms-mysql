package com.middil
package activity
package xml

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
  containerType: String,
  label: String,
  randomize: String,
  weight: String,
  elements: Seq[ContainerElements],
)

object Container {

  def toElem(container: Container): scala.xml.Elem = {
    <container x={container.x}
               y={container.y}
               width={container.width}
               height={container.height}
               borderStyle={container.borderStyle}
               borderColor={container.borderColor}
               backgroundColor={container.backgroundColor}
               backgroundAlpha={container.backgroundAlpha}
               borderPadding={container.borderPadding}
               borderThickness={container.borderThickness}
               borderRadius={container.borderRadius}
               rotation={container.rotation}
               type={container.containerType}
               label={container.label}
               randomize={container.randomize}
               weight={container.weight}>
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

/**
 * Elements
 * {{{
 * <elements/>
 * }}}
 */
case class ContainerElements(
  fontSize: String,
  fontWeight: String,
  fontFamily: String,
  fontPosture: String,
  textDecoration: String,
  lineHeight: String,
  fontColor: String,
  rearrange: String,
  elements: Seq[ContainerElement]
)

object ContainerElements {

  def toElem(containerElements: ContainerElements): scala.xml.Elem = {
    <elements fontSize={containerElements.fontSize}
              fontWeight={containerElements.fontWeight}
              fontFamily={containerElements.fontFamily}
              fontPosture={containerElements.fontPosture}
              textDecoration={containerElements.textDecoration}
              lineHeight={containerElements.lineHeight}
              fontColor={containerElements.fontColor}
              rearrange={containerElements.rearrange}>
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

/**
 * Element
 * {{{
 * <element/>
 * }}}
 */
case class ContainerElement(
  fontSize: String,
  fontWeight: String,
  fontFamily: String,
  fontPosture: String,
  textDecoration: String,
  lineHeight: String,
  fontColor: String,
  elementType: Option[ElementType],
  elementText: Option[ElementText]
)

object ContainerElement {

  def toElem(containerElement: ContainerElement): scala.xml.Elem = {
    <element fontSize={containerElement.fontSize}
             fontWeight={containerElement.fontWeight}
             fontFamily={containerElement.fontFamily}
             fontPosture={containerElement.fontPosture}
             textDecoration={containerElement.textDecoration}
             lineHeight={containerElement.lineHeight}
             fontColor={containerElement.fontColor}>
      { containerElement.elementType.map(
          ElementType.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty) 
      }
      { containerElement.elementText.map(
          ElementText.toElem(_)
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

package com.middil
package activity
package xml

/**
 * Hotspot
 * {{{
 * <hotspot type="rectangle">
 *   <associations>draggable1</associations>
 * <hotspot type="rectangle">
 * }}}
 */
case class Hotspot(
  hotspotType: String,
  itemName: String,
  width: Long,
  height: Long,
  x: Long,
  y: Long,
  rotation: Long,
  dropOpacity: Long,
  isDragDropContainer: String,
  associations: Option[Associations]
)

object Hotspot {

  def toElem(hotspot: Hotspot): scala.xml.Elem = {
    <hotspot type={hotspot.hotspotType}
             itemName={hotspot.itemName}
             width={hotspot.width.toString}
             height={hotspot.height.toString}
             x={hotspot.x.toString}
             y={hotspot.y.toString}
             rotation={hotspot.rotation.toString}
             dropOpacity={hotspot.dropOpacity.toString}
             isDragDropContainer={hotspot.isDragDropContainer}>
      { hotspot.associations.map(
          Associations.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty)
      }
    </hotspot>
  }

  def toElem(hotspots: Seq[Hotspot]): scala.xml.NodeSeq = {
    for {
      hotspot <- hotspots
    } yield {
      Hotspot.toElem(hotspot)
    }
  }
}

/**
 * Associations
 * {{{
 * <associations>draggable1</associations>
 * }}}
 */
case class Associations(
  association: String
)

object Associations {
  def toElem(associations: Associations): scala.xml.Elem = {
    <associations>{associations.association}</associations>
  }
}

package com.middil
package activity
package xml

/**
 * Drag and drop activity (ADD)
 * {{{
 * <data type="ADD" ... />
 * }}}
 *
 * Child elements:
 *
 *   - [[com.middil.activity.xml.Container Container]]
 *     - [[com.middil.activity.xml.ContainerElements ContainerElements]]
 *       - [[com.middil.activity.xml.ContainerElement ContainerElement]]
 *         - [[com.middil.activity.xml.ElementType ElementText]]
 *         - [[com.middil.activity.xml.ElementType ElementType]]
 *   - [[com.middil.activity.xml.Draggable Draggable]]
 *   - [[com.middil.activity.xml.ElementCount ElementCount]]
 *   - [[com.middil.activity.xml.Element Element]]
 *     - [[com.middil.activity.xml.ElementAudio ElementAudio]]
 *     - [[com.middil.activity.xml.ElementMedia ElementMedia]]
 *   - [[com.middil.activity.xml.Group Group]]
 *   - [[com.middil.activity.xml.HotspotList HotspotList]]
 *   - [[com.middil.activity.xml.Hotspot Hotspot]]
 *     - [[com.middil.activity.xml.Associations Associations]]
 *   - [[com.middil.activity.xml.Image Image]]
 *   - [[com.middil.activity.xml.Message Message]]
 *   - [[com.middil.activity.xml.TimeoutIndex TimeoutIndex]]
 *   - [[com.middil.activity.xml.Timeout Timeout]]
 *   - [[com.middil.activity.xml.Weight Weight]]
 */
case class ADD(
  author: String,
  saved: String,
  randomize: String,
  images: Seq[Image],
  timeout: Option[Timeout],
  timeoutIndex: Option[TimeoutIndex],
  timer: Option[String],
  scored: Option[String],
  attempts: Option[String],
  messages: Seq[Message],
  groups: Seq[Group],
  elementCount: Option[ElementCount],
  draggables: Seq[Draggable],
  elements: Seq[Element],
  containers: Seq[Container],
  hotspotLists: Seq[HotspotList],
  hotspots: Seq[Hotspot],
  weight: Option[Weight]
)

object ADD {

  def toElem(add: ADD): scala.xml.Elem = {
    <data type="ADD" author={add.author} saved={add.saved} randomize={add.randomize}>
      { Image.toElem(add.images) }
      { add.timeout.map {
          Timeout.toElem(_)
        }.getOrElse(scala.xml.NodeSeq.Empty)
      }
      { add.timeoutIndex.map {
          TimeoutIndex.toElem(_)
        }.getOrElse(scala.xml.NodeSeq.Empty)
      }
      <timer>{ add.timer.getOrElse("down") }</timer>
      <scored>{ add.scored.getOrElse("true") }</scored>
      <attempts>{ add.attempts.getOrElse("true") }</attempts>
      { Message.toElem(add.messages) }
      { Group.toElem(add.groups) }
      { add.elementCount.map {
          ElementCount.toElem(_)
        }.getOrElse(scala.xml.NodeSeq.Empty)
      }
      { Draggable.toElem(add.draggables) }
      { Element.toElem(add.elements) }
      { Container.toElem(add.containers) }
      { HotspotList.toElem(add.hotspotLists) }
      { Hotspot.toElem(add.hotspots) }
      { add.weight.map{
          Weight.toElem(_)
        }.getOrElse(scala.xml.NodeSeq.Empty)
      }
    </data>
  }
}

/**
 * Image
 * {{{
 * <image/>
 * }}}
 */
case class Image(
)

object Image {

  def toElem(image: Image): scala.xml.Elem = {
    <image/>
  }

  def toElem(images: Seq[Image]): scala.xml.NodeSeq = {
    if (images.isEmpty) {
      <image/>
    } else {
      val xml: Seq[scala.xml.Elem] = for {
        image <- images
      } yield {
        Image.toElem(image)
      }
      scala.xml.NodeSeq.fromSeq(xml)
    }
  }
}

/**
 * Timeout
 * {{{
 * <timeout show="false">0</timeout>
 * }}}
 */
case class Timeout(
  show: String,
  duration: Option[Long]
)

object Timeout {

  def toElem(timeout: Timeout): scala.xml.Elem = {
    <timeout show={timeout.show}>{timeout.duration}</timeout>
  }
}

/**
 * Timeout index
 * {{{
 * <timeoutIndex show="true">0</timeoutIndex>
 * }}}
 */
case class TimeoutIndex(
  show: String,
  index: Option[Long]
)

object TimeoutIndex {

  def toElem(timeoutIndex: TimeoutIndex): scala.xml.Elem = {
    <timeoutIndex show={timeoutIndex.show}>{timeoutIndex.index}</timeoutIndex>
  }
}

/**
 * Group
 * {{{
 *  <group/>
 * }}}
 */
case class Group(
)

object Group {

  def toElem(group: Group): scala.xml.Elem = {
    <group/>
  }

  def toElem(groups: Seq[Group]): scala.xml.NodeSeq = {
    if (groups.isEmpty) {
      <group/>
    } else {
      val xml: Seq[scala.xml.Elem] = for {
        group <- groups
      } yield {
        Group.toElem(group)
      }

      scala.xml.NodeSeq.fromSeq(xml)
    }
  }
}

/**
 * Element count
 * {{{
 * <elementcount targetCount="0" dragCount="4"/>
 * }}}
 */
case class ElementCount(
  targetCount: Long,
  dragCount: Long
)

object ElementCount {

  def toElem(elementCount: ElementCount): scala.xml.Elem = {
    <elementcount targetCount={elementCount.targetCount.toString}
                  dragCount={elementCount.dragCount.toString} />
  }
}

/** Draggable
 * {{{
 * <draggable>draggable1</draggable>
 * }}}
 */
case class Draggable(
  name: Option[String]
)

object Draggable {

  def toElem(draggable: Draggable): scala.xml.Elem = {
    <draggable>{draggable.name.getOrElse("")}</draggable>
  }

  def toElem(draggables: Seq[Draggable]): scala.xml.NodeSeq = {
    for {
      draggable <- draggables
    } yield {
      Draggable.toElem(draggable)
    }
  }
}

/**
 * Hotspot
 * {{{
 *  <hotspotlist>hotspot1</hotspotlist>
 * }}}
 */
case class HotspotList(
  name: String
)

object HotspotList {

  def toElem(hotspotList: HotspotList): scala.xml.Elem = {
    <hotspotlist>{hotspotList.name}</hotspotlist>
  }

  def toElem(hotspotLists: Seq[HotspotList]): scala.xml.NodeSeq = {
    for {
      hotspotList <- hotspotLists
    } yield {
      HotspotList.toElem(hotspotList)
    }
  }
}

/**
  * Weight
  * {{{
  * <weight>
  *   <total>0</total>
  * </weight>
  * }}}
  */
case class Weight(
  total: Option[Total]
)

object Weight {
  def toElem(weight: Weight): scala.xml.Elem = {
    <weight>
     { weight.total.map(
         Total.toElem(_)
       ).getOrElse(scala.xml.NodeSeq.Empty)
     }
    </weight>
  }
}

/**
  * Total
  * {{{
  * <total>0</total>
  * }}}
  */
case class Total(
  weight: Option[Long]
)

object Total {
  def toElem(total: Total): scala.xml.Elem = {
    <total>{total.weight.getOrElse(0)}</total>
  }
}

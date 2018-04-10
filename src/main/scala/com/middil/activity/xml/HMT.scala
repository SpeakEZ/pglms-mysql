package com.middil
package activity
package xml

/**
 * Highlightable movable text (HMT)
 * {{{
 * <data type="HMT" ... />
 * }}}
 *
 * Child elements:
 *
 *   - [[com.middil.activity.xml.Activity]]
 *     - [[com.middil.activity.xml.hmt.Containers Containers]]
 *       - [[com.middil.activity.xml.hmt.Container Container]]
 *       - [[com.middil.activity.xml.hmt.ContainerElements ContainerElements]]
 *         - [[com.middil.activity.xml.hmt.ContainerElement ContainerElement]]
 *           - [[com.middil.activity.xml.hmt.ElementType ElementType]]
 *           - [[com.middil.activity.xml.hmt.ElementText ElementText]]
 *           - [[com.middil.activity.xml.hmt.ElementTextArea ElementTextArea]]
 *           - [[com.middil.activity.xml.hmt.ElementImage ElementImage]]
 *             - [[com.middil.activity.xml.URL URL]]
 *             - [[com.middil.activity.xml.Thumbnail Thumbnail]]
 *             - [[com.middil.activity.xml.ElementAudio ElementAudio]]
 *           - [[com.middil.activity.xml.ElementMedia ElementMedia]]
 *   - [[com.middil.activity.xml.MetaData MetaData]]
 *   - [[com.middil.activity.xml.Weight Weight]]
 *
 * @see [[com.pglms.activity.convert.com.middil.HMT$]]
 */
case class HMT(
  author: String,
  saved: String,
  version: String,
  activity: Option[Activity]
)

object HMT {

  type container = hmt.Container
  type containers = hmt.Containers

  def toElem(hmt: HMT): scala.xml.Elem = {
    <data type="HMT" author={hmt.author} saved={hmt.saved} version={hmt.version}>
      { hmt.activity.map {
          Activity.toElem(_)
        }.getOrElse(scala.xml.NodeSeq.Empty)
      }
    </data>
  }
}

case class Activity(
  weight: Option[Weight],
  metaData: Option[MetaData],
  containers: Seq[HMT.containers]
)

object Activity {

  def toElem(activity: Activity): scala.xml.Elem = {
    <activity>
      { activity.weight.map{
          Weight.toElem(_)
        }.getOrElse(scala.xml.NodeSeq.Empty)
      }
      { activity.metaData.map {
          MetaData.toElem(_)
        }.getOrElse(scala.xml.NodeSeq.Empty)
      }
      { hmt.Containers.toElem(activity.containers) }
    </activity>
  }
}

case class MetaData(
  activityType: String
)

object MetaData {

  def toElem(metaData: MetaData): scala.xml.Elem = {
    <metadata>
      <type>{metaData.activityType}</type>
    </metadata>
  }
}

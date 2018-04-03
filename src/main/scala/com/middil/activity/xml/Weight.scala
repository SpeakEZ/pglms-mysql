package com.middil
package activity
package xml

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

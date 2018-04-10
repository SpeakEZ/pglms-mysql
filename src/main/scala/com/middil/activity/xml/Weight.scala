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
  total: Option[Total],
  extra: Option[Extra]
)

object Weight {
  def toElem(weight: Weight): scala.xml.Elem = {
    <weight>
      { weight.total.map(
          Total.toElem(_)
        ).getOrElse(scala.xml.NodeSeq.Empty)
      }
      { weight.extra.map(
          Extra.toElem(_)
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

/**
  * Extra
  * {{{
  * <extra>0</extra>
  * }}}
  */
case class Extra(
  amount: Option[Long]
)

object Extra {
  def toElem(extra: Extra): scala.xml.Elem = {
    <extra>{extra.amount.getOrElse(0)}</extra>
  }
}

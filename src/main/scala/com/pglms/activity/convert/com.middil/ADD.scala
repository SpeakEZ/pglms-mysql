package com.pglms
package activity
package convert.com.middil

/**
 * Activity drag and drop converter.
 *
 * Convert from
 * [[com.pglms.activity.Drag]]
 * to
 * [[com.middil.activity.xml.ADD]]
 */
object ADD {

  def fromDrag(drag: Drag) = com.middil.activity.xml.ADD(
    author       = "SpeakEZ 2.5",
    saved        = "Wed Mar 14 16:23:00 GMT-0400 2018",
    randomize    = "true",
    images       = Seq.empty[com.middil.activity.xml.Image],
    timeout      = Some(
      com.middil.activity.xml.Timeout(
        show = "false",
        duration = Some(0L)
      )
    ),
    timeoutIndex = Some(
      com.middil.activity.xml.TimeoutIndex(
        show = "true",
        index = Some(0)
      )
    ),
    timer        = None,
    scored       = None,
    attempts     = Some(3L),
    messages     = Seq.empty[com.middil.activity.xml.Message],
    groups       = Seq.empty[com.middil.activity.xml.Group],
    elementCount = Some(
      com.middil.activity.xml.ElementCount(
        targetCount = Some(0L),
        dragCount   = Some(totalElementCount(drag))
      )
    ),
    draggables   = makeDraggables(drag),
    elements     = makeElements(drag),
    containers   = makeContainers(drag),
    hotspotLists = makeHotspotLists(drag),
    hotspots     = makeHotspots(drag),
    weight       = Some(
      com.middil.activity.xml.Weight(
        total = Some(
          com.middil.activity.xml.Total(
            weight = Some(0L)
          )
        )
      )
    )
  )

  def totalElementCount(drag: Drag): Long = {
    // drag.groups.map(_.questions.length.toLong).fold(0L)(_ + _)
    drag.groups.take(1).map(_.questions.length.toLong).fold(0L)(_ + _)
  }

  def makeDraggables[T](drag: Drag): Seq[com.middil.activity.xml.Draggable] = {
    for {
      group <- drag.groups.take(1)
      (question, i) <- group.questions.zipWithIndex
    } yield {
      com.middil.activity.xml.Draggable(
        name = "draggable" + (i + 1).toString
      )
    }
  }

  def makeElements(drag: Drag): Seq[com.middil.activity.xml.Element] = {
    for {
      group <- drag.groups.take(1)
      (question, i) <- group.questions.zipWithIndex
    } yield {
      val answer: String = question.answers.map(_.text.getOrElse("")).mkString(" ")
      val elementName    = "draggable" + (i + 1).toString
      val elementX       = 50 + (350 * i)
      val elementY       = 300 + (100 * i)
      val elementWidth   = 250
      val elementHeight  = 100
      com.middil.activity.xml.Element(
        hideText              = "false",
        hideBorder            = "false",
        elementType           = "Draggable Element",
        itemName              = elementName,
        mediaType             = "Text",
        verticalTextAlignment = "alignMiddle",
        insideTextHTML        =
          s"""|<HTML>
              |<BODY>
              |<P ALIGN="center">
              |<FONT FACE="Arial" SIZE="12" COLOR="#000000" LETTERSPACING="0" KERNING="1">
              |<FONT SIZE="18">${answer}</FONT>
              |</FONT>
              |</P>
              |</BODY>
              |</HTML>""".stripMargin.lines.mkString,
        roundedEdges          = "false",
        scaleInTarget         = "false",
        bkColor               = "16777215",
        width                 = elementWidth.toLong,
        height                = elementHeight.toLong,
        x                     = elementX.toLong,
        y                     = elementY.toLong,
        rotation              = 0,
        dropOpacity           = BigDecimal("0.8"),
        autoPlay              = "false",
        mute                  = "false",
        backgroundOpacity     = BigDecimal("0.75"),
        maintainAspectRatio   = "true",
        notTarget             = "true",
        media                 = Some(
          com.middil.activity.xml.ElementMedia(
            mediaThumbnail = ""
          )
        ),
        audio                 = Some(
          com.middil.activity.xml.ElementAudio(
            iconColor = "229740"
          )
        )
      )
    }
  }

  def makeContainers(drag: Drag): Seq[com.middil.activity.xml.Container] = {
    for {
      group <- drag.groups.take(1)
      (question, i) <- group.questions.zipWithIndex
    } yield {
      val containerName   = "draggable" + (i + 1).toString
      val containerX      =  275
      val containerY      = 50 + (100 * i)
      val containerWidth  = 250
      val containerHeight = 100
      com.middil.activity.xml.Container(
        x               = containerX.toString,
        y               = containerY.toString,
        width           = containerWidth.toString,
        height          = containerHeight.toString,
        borderStyle     = "none",
        borderColor     = "0",
        backgroundColor = "0",
        backgroundAlpha = "0",
        borderPadding   = "0",
        borderThickness = "0",
        borderRadius    = "0",
        rotation        = "0",
        containerType   = "interactive_text",
        label           = "",
        randomize       = "false",
        weight          = "0",
        elements        = Seq(
          com.middil.activity.xml.ContainerElements(
            fontSize       = "14",
            fontWeight     = "normal",
            fontFamily     = "Arial",
            fontPosture    = "normal",
            textDecoration = "none",
            lineHeight     = "0",
            fontColor      = "0",
            rearrange      = "false",
            elements       = Seq(
              com.middil.activity.xml.ContainerElement(
                fontSize       = "14",
                fontWeight     = "normal",
                fontFamily     = "Arial",
                fontPosture    = "normal",
                textDecoration = "undefined",
                lineHeight     = "0",
                fontColor      = "0",
                elementType    = Some(
                  com.middil.activity.xml.ElementType(
                    toolTip     = "",
                    elementType = "text"
                  )
                ),
                elementText    = Some(
                  com.middil.activity.xml.ElementText(
                    elementText = question.text.getOrElse("")
                  )
                )
              )
            )
          )
        )
      )
    }
  }

  def makeHotspotLists[T](drag: Drag): Seq[com.middil.activity.xml.HotspotList] = {
    for {
      group <- drag.groups.take(1)
      (question, i) <- group.questions.zipWithIndex
    } yield {
      com.middil.activity.xml.HotspotList(
        name = "hotspot" + (i + 1).toString
      )
    }
  }

  def makeHotspots[T](drag: Drag): Seq[com.middil.activity.xml.Hotspot] = {
    for {
      group <- drag.groups.take(1)
      (question, i) <- group.questions.zipWithIndex
    } yield {
      val hotspotName   = "hotspot" + (i + 1).toString
      val draggableName = "draggable" + (i + 1).toString
      val hotspotX      = 450
      val hotspotY      = 50 + (100 * i)
      val hotspotWidth  = 250
      val hotspotHeight = 100
      com.middil.activity.xml.Hotspot(
        hotspotType         =  "rectangle",
        itemName            =  hotspotName,
        width               =  hotspotWidth.toLong,
        height              =  hotspotHeight.toLong,
        x                   =  hotspotX.toLong,
        y                   =  hotspotY.toLong,
        rotation            =  0,
        dropOpacity         =  1,
        isDragDropContainer =  "true",
        associations        = Some(com.middil.activity.xml.Associations(draggableName))
      )
    }
  }
}

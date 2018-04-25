package com.pglms
package activity

/**
 * Story activity
 * {{{
 * <data>
 *   <image>...</image>
 *   <audio>...</audio>
 *   <speakers>
 *     <speaker>...</speaker>
 *     <speaker>...</speaker>
 *   </speakers>
 *   <lines>
 *     <line>...</line>
 *     ...
 *   </lines>
 * </data>
 * }}}
 *
 * Child elements:
 *
 *   - [[com.pglms.activity.Image Image]]
 *   - [[com.pglms.activity.Audio Audio]]
 *   - [[com.pglms.activity.Speakers Speakers]]
 *     - [[com.pglms.activity.Speaker Speaker]]
 *   - [[com.pglms.activity.Lines Lines]]
 *     - [[com.pglms.activity.Line Line]]
 *
 * @see [[com.pglms.activity.convert.com.middil.HMT$]]
 */
case class Story(
  image: Option[Image],
  audio: Option[Audio],
  speakers: Option[Speakers],
  lines: Option[Lines]
)

object Story {

  def fromElem(el: scala.xml.Elem): Option[Story] = {
    for {
      data <- Some(el) if el.label == "data" // (el \ "data").headOption
    } yield Story(
      image    = Image.fromElem(data),
      audio    = Audio.fromElem(data),
      speakers = Speakers.fromElem(data),
      lines    = Lines.fromElem(data)
    )
  }

  def fromFile(file: String): Option[Story] = {
    fromElem(scala.xml.XML.loadFile(file))
  }
}

/**
 * Image
 * {{{
 * <image/>
 * }}}
 */
case class Image(
  url: String
)

object Image {
  def fromElem(el: scala.xml.Elem): Option[Image] = {
    for {
      image <- (el \ "image").headOption
    } yield Image(
      url = image.text
    )
  }
}

/**
 * Audio
 * {{{
 * <audio/>
 * }}}
 */
case class Audio(
  url: String
)

object Audio {
  def fromElem(el: scala.xml.Elem): Option[Audio] = {
    for {
      audio <- (el \ "audio").headOption
    } yield Audio(
      url = audio.text
    )
  }
}

/**
 * Speakers
 * {{{
 * <speakers>
 *   <speaker>...</speaker>
 *   ...
 * </speakers>
 * }}}
 */
case class Speakers(
  speakers: Seq[Speaker]
)

object Speakers {
  def fromElem(el: scala.xml.Elem): Option[Speakers] = {
    for {
      speakers <- (el \ "speakers").headOption
    } yield Speakers(
      speakers = Speaker.fromElem(speakers)
    )
  }
}

/**
 * Speaker
 * {{{
 * <speaker num="1">...</speaker>
 * }}}
 */
case class Speaker(
  num: String,
  speaker: String
)

object Speaker {
  def fromElem(el: scala.xml.Node): Seq[Speaker] = {
    for {
      speaker <- (el \ "speaker")
    } yield Speaker(
      num     = speaker \@ "num",
      speaker = speaker.text
    )
  }
}

/**
 * Lines
 * {{{
 * <lines>
 *   <line>...</line>
 *   ...
 * </lines>
 * }}}
 */
case class Lines(
  lines: Seq[Line]
)

object Lines {
  def fromElem(el: scala.xml.Elem): Option[Lines] = {
    for {
      lines <- (el \ "lines").headOption
    } yield Lines(
      lines = Line.fromElem(lines)
    )
  }
}

/**
 * Line
 * {{{
 * <line speaker="1" cue="">What are you saying?</line>
 * }}}
 */
case class Line(
  speaker: String,
  cue: String,
  line: String
)

object Line {
  def fromElem(el: scala.xml.Node): Seq[Line] = {
    for {
      line <- (el \ "line")
    } yield Line(
      speaker = line \@ "speaker",
      cue     = line \@ "cue",
      line    = line.text
    )
  }
}

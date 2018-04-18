package com.middil
package activity
package xml

/**
 * Element audio
 * {{{
 * <audio iconColor="229740"/>
 * }}}
 */
case class ElementAudio(
  iconColor: String,
  source: Option[AudioSource]
)

object ElementAudio {

  def toElem(elementAudio: ElementAudio): scala.xml.Elem = {
    if (elementAudio.source.isEmpty) {
      <audio iconColor={elementAudio.iconColor}/>
    } else {
      <audio>
        { elementAudio.source.map(
            AudioSource.toElem(_)
          ).getOrElse(scala.xml.NodeSeq.Empty)
        }
      </audio>
    }
  }
}

/**
 * Audio source
 * {{{
 * <source/>
 * }}}
 */
case class AudioSource(
)

object AudioSource {

  def toElem(audioSource: AudioSource): scala.xml.Elem = {
    <source/>
  }
}

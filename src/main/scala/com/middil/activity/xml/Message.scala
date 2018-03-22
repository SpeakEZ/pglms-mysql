package com.middil
package activity
package xml

/**
 * Message
 * {{{
 * <message>
 *   <text/>
 *   <audio/>
 * </message>
 * }}}
 */
case class Message(
  messageText: Option[MessageText],
  messageAudio: Option[MessageAudio]
)

object Message {

  def toElem(message: Message): scala.xml.Elem = {
    <message>
      <text/>
      <audio/>
    </message>
  }

  def toElem(messages: Seq[Message]): scala.xml.NodeSeq = {
    if (messages.isEmpty) {
      <message>
        <text/>
        <audio/>
      </message>
    } else {
      val xml: Seq[scala.xml.Elem] = for {
        message <- messages
      } yield {
        Message.toElem(message)
      }
      scala.xml.NodeSeq.fromSeq(xml)
    }
  }
}

/**
 * Message text
 * {{{
 * <text/>
 * }}}
 */
case class MessageText(
)

object MessageText {
  def toElem(text: MessageText): scala.xml.Elem = {
    <text/>
  }
}

/**
 * Message audio
 * {{{
 * <audio/>
 * }}}
 */
case class MessageAudio(
)

object MessageAudio {
  def toElem(audio: MessageAudio): scala.xml.Elem = {
    <audio/>
  }
}

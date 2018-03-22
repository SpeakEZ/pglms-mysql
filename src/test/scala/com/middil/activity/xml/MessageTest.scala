package com.middil
package activity
package xml

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class MessageTest
    extends FlatSpec
    with Matchers {

  "Message.toElem" should
  "return XML" in {

    val message = Message(
      messageText = Some(MessageText(
      )),
      messageAudio = Some(MessageAudio(
      )),
    )
    val xml = Message.toElem(message)

    val expected: scala.xml.Elem =
      <message>
        <text/>
        <audio/>
      </message>

    val pp = new scala.xml.PrettyPrinter(72, 2)

    pp.format(xml) should be (pp.format(expected))
  }
}

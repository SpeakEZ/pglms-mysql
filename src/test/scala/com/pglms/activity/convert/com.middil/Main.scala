package com.pglms
package activity
package convert.com.middil

object Main {

  def main(args: Array[String]): Unit = {

    val dragFile = "src/test/resources/activity/drag.xml"

    val addFile = "src/test/resources/activity/middil.com/add.xml"

    val pp = new scala.xml.PrettyPrinter(1000, 2)

    // val drag: Option[Drag] = Drag.fromFile(dragFile)
    // val add: com.middil.activity.xml.ADD = ADD.fromDrag(drag)
    // scala.xml.XML.save(addFile, add)

    for {
      drag <- Drag.fromFile(dragFile): Option[Drag]
    } yield {
      val addPath = java.nio.file.Paths.get(addFile)

      val add: com.middil.activity.xml.ADD = ADD.fromDrag(drag)

      // scala.xml.XML.save(addFile, com.middil.activity.xml.ADD.toElem(add))

      val xml = com.middil.activity.xml.ADD.toElem(add)
      java.nio.file.Files.write(addPath, pp.format(xml).getBytes)

      println(s"Wrote ${addFile}")
    }

  }
}

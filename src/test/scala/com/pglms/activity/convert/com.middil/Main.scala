package com.pglms
package activity
package convert.com.middil

object Main {

  def main(args: Array[String]): Unit = {
    args(0) match {
      case "commentaryToHMT" => commentaryToHMT(args.tail)
      case "dragToADD" => dragToADD(args.tail)
    }
  }

  def dragToADD(args: Array[String]): Unit = {

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

  def commentaryToHMT(args: Array[String]): Unit = {

    val commentaryFile = "src/test/resources/activity/commentary.html"

    val hmtFile = "src/test/resources/activity/middil.com/hmt.xml"

    val pp = new scala.xml.PrettyPrinter(1000, 2)

    // val commentary: Option[Commentary] = Commentary.fromFile(commentaryFile)
    // val hmt: com.middil.activity.xml.HMT = HMT.fromCommentary(commentary)
    // scala.xml.XML.save(hmtFile, hmt)

    for {
      commentary <- Commentary.fromFile(commentaryFile): Option[Commentary]
    } yield {
      val hmtPath = java.nio.file.Paths.get(hmtFile)

      val hmt: com.middil.activity.xml.HMT = HMT.fromCommentary(commentary)

      // scala.xml.XML.save(hmtFile, com.middil.activity.xml.HMT.toElem(hmt))

      val xml = com.middil.activity.xml.HMT.toElem(hmt)
      java.nio.file.Files.write(hmtPath, pp.format(xml).getBytes)

      println(s"Wrote ${hmtFile}")
    }

  }
}

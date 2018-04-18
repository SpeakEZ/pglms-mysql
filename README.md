PGLMS MySQL
===========

Requires [sbt] to compile and run the system.

### Run the test suite

    $ sbt
    > test

### Test program

    $ sbt
    > test:run commentaryToADD src/test/resources/activity/drag.xml
    [info] Running com.pglms.activity.convert.com.middil.Main
    Wrote src/test/resources/activity/middil.com/add.xml
    [success] Total time: 2 s
    > test:run commentaryToADD src/test/resources/activity/commentary.xml
    [info] Running com.pglms.activity.convert.com.middil.Main 
    Wrote src/test/resources/activity/middil.com/hmt.xml
    [success] Total time: 8 s

The program above will:

1. Open a file, `drag.xml`, containg PGLMS drag-and-drop XML.
2. Convert the activity to a CAP drag-and-drop.
3. Write the CAP XML to a file, `add.xml`.

  [sbt]: http://www.scala-sbt.org/download.html

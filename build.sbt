// build.sbt --- sbt settings

scalaVersion := "2.12.4"

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-encoding",
  "utf8",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-inaccessible"
)

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= List(
  "org.slf4j"               % "slf4j-nop"             % "1.7.25",
  "mysql"                   % "mysql-connector-java"  % "5.1.45",
  "com.h2database"          % "h2"                    % "1.4.196",
  "org.xerial"              % "sqlite-jdbc"           % "3.8.11.2",
  "joda-time"               % "joda-time"             % "2.9.9",
  "org.scala-lang.modules" %% "scala-xml"             % "1.1.0",
  "org.typelevel"          %% "cats-core"             % "1.0.1",
  "org.tpolecat"           %% "doobie-core"           % "0.5.1",
  "org.tpolecat"           %% "doobie-h2"             % "0.5.1",              // H2 driver 1.4.196 + type mappings.
  "org.tpolecat"           %% "doobie-hikari"         % "0.5.1",              // HikariCP transactor.
  "org.tpolecat"           %% "doobie-postgres"       % "0.5.1",              // Postgres driver 42.2.1 + type mappings.
  "org.tpolecat"           %% "doobie-specs2"         % "0.5.1"     % "test", // Specs2 support for typechecking statements.
  "org.tpolecat"           %% "doobie-scalatest"      % "0.5.1"     % "test", // ScalaTest support for typechecking statements.
  "org.scalactic"          %% "scalactic"             % "3.0.5",
  "org.scalatest"          %% "scalatest"             % "3.0.5"     % "test",
  "org.specs2"             %% "specs2-core"           % "4.0.2"     % "test",
  "org.specs2"             %% "specs2-mock"           % "4.0.2"     % "test",
  "org.specs2"             %% "specs2-matcher-extra"  % "4.0.2"     % "test",
  "org.scalacheck"         %% "scalacheck"            % "1.13.5"    % "test"
)

testOptions in Test += Tests.Argument("-oD")

parallelExecution in Test := false

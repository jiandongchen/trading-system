ThisBuild / organization := "com.jaden"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.13"

ThisBuild / resolvers ++= Seq(
  "Local Maven Repository" at "file://" + Path.userHome.absolutePath + ".m2/repository"
  , "Ali Maven" at "https://maven.aliyun.com/repository/central"
  , "Repo1 Central" at "https://repo1.maven.org/maven2/"
)

lazy val tradingSystem = (project in file("."))
  .dependsOn(core, dataAcquisition, strategyExecution, orderManagement, riskManagement)
  .settings(
    name := "trading-system"
  )

lazy val core = (project in file("core"))
  .settings(
    name := "core"
  )

lazy val dataAcquisition = (project in file("data-acquisition"))
  .settings(
    name := "data-acquisition"
    , libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor-typed" % "2.8.5"
      , "com.softwaremill.sttp.client3" %% "core" % "3.1.9"
      , "org.json4s" %% "json4s-jackson" % "4.0.7"
    )
  )

lazy val strategyExecution = (project in file("strategy-execution"))
  .settings(
    name := "strategy-execution"
  )

lazy val orderManagement = (project in file("order-management"))
  .settings(
    name := "order-management"
  )

lazy val riskManagement = (project in file("risk-management"))
  .settings(
    name := "risk-management"
  )
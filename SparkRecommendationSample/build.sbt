name := "SparkRecommendationSample"

version := "1.0"

//Versions
//version := "1.21.2"
scalaVersion := "2.11.8"
val sparkVersion = "2.0.2"
val sl4jVersion = "1.7.10"
val log4jVersion = "1.2.17"
val scalaTestVersion = "2.2.6"
val scalaLoggingVersion = "3.5.0"
val sparkTestingBaseVersion = "2.0.2_0.6.0"
val jodaTimeVersion = "2.9.6"
val jodaConvertVersion = "1.8.1"
val sparkCassandraConnectorVersion = "2.0.2"
val typeSafeConfigVersion = "1.3.1"


//libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.1"
//libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.1"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided")
  .map(_.excludeAll(
    ExclusionRule(organization = "org.scalacheck"),
    ExclusionRule(organization = "org.scalactic"),
    ExclusionRule(organization = "org.scalatest")
  ))

libraryDependencies ++= Seq(
  "com.datastax.spark" %% "spark-cassandra-connector" % sparkCassandraConnectorVersion,
  "org.slf4j" % "slf4j-api" % sl4jVersion,
  "org.slf4j" % "slf4j-log4j12" % sl4jVersion exclude("log4j", "log4j"),
  "log4j" % "log4j" % log4jVersion % "provided",
  "org.joda" % "joda-convert" % jodaConvertVersion,
  "joda-time" % "joda-time" % jodaTimeVersion,
  "com.typesafe" % "config" % typeSafeConfigVersion
)



/*
==================
Build.sbt example
==================

name := "NZZ Algorithms"

import uk.gov.hmrc.gitstamp.GitStampPlugin._
Seq( gitStampSettings: _* )

//Versions
version := "1.21.2"
scalaVersion := "2.11.8"
val sparkVersion = "2.0.2"
val sl4jVersion = "1.7.10"
val log4jVersion = "1.2.17"
val scalaTestVersion = "2.2.6"
val scalaLoggingVersion = "3.5.0"
val sparkTestingBaseVersion = "2.0.2_0.6.0"
val jodaTimeVersion = "2.9.6"
val jodaConvertVersion = "1.8.1"
val sparkCassandraConnectorVersion = "2.0.2"
val jacksonVersion = "2.4.4"
val jsonAssertVersion = "1.2.3"
val typeSafeConfigVersion = "1.3.1"
val guavaVersion = "15.0"
val commonsLangVersion = "3.6"
val commonsIOVersion = "1.3.2"
val jsoupVersion = "1.10.3"
val mockitoVersion = "1.10.19"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided")
  .map(_.excludeAll(
    ExclusionRule(organization = "org.scalacheck"),
    ExclusionRule(organization = "org.scalactic"),
    ExclusionRule(organization = "org.scalatest")
  ))

libraryDependencies ++= Seq(
  "com.datastax.spark" %% "spark-cassandra-connector" % sparkCassandraConnectorVersion,
  "org.slf4j" % "slf4j-api" % sl4jVersion,
  "org.slf4j" % "slf4j-log4j12" % sl4jVersion exclude("log4j", "log4j"),
  "log4j" % "log4j" % log4jVersion % "provided",
  "org.joda" % "joda-convert" % jodaConvertVersion,
  "joda-time" % "joda-time" % jodaTimeVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "com.typesafe" % "config" % typeSafeConfigVersion,
  "com.google.guava" % "guava" % guavaVersion,
  "org.jsoup" % "jsoup" % jsoupVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "com.holdenkarau" %% "spark-testing-base" % sparkTestingBaseVersion % "test",
  "org.skyscreamer" % "jsonassert" % jsonAssertVersion % "test",
  "org.apache.commons" % "commons-lang3" % commonsLangVersion % "test",
  "org.apache.commons" % "commons-io" % commonsIOVersion % "test",
  "org.mockito" % "mockito-all" % mockitoVersion % "test"
)

assemblyJarName in assembly := "nzz-dnidatalayer.jar"

run in Compile := Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))
runMain in Compile := Defaults.runMainTask(fullClasspath in Compile, runner in(Compile, run))

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

// Spark does not support parallel tests and requires JVM fork
parallelExecution in Test := false

fork in Test := true
javaOptions in Test ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

 */
name := """car-shop"""
organization := "space.zhdanov.laboratory"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  evolutions,
  "org.mybatis" % "mybatis" % "3.5.4",
  "org.mybatis" % "mybatis-guice" % "3.12" exclude("org.slf4j", "slf4j-api"),
  "com.google.inject.extensions" % "guice-multibindings" % "4.0",
  "com.h2database" % "h2" % "1.4.188"
)


unmanagedResourceDirectories in Compile += baseDirectory(_ / "app" / "conf" / "space" / "zhdanov" / "laboratory" / "carshop" / "repositories").value

// but filter out java and html files that would then also be copied to the classpath
excludeFilter in Compile in unmanagedResources := "*.java" || "*.html"
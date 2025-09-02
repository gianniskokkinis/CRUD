name := """CRUD"""
organization := "synergic"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.16"

libraryDependencies ++= Seq(
	guice,


	// Jakarta Persistence API
	"jakarta.persistence" % "jakarta.persistence-api" % "3.1.0",

	// Hibernate ORM
	"org.hibernate.orm" % "hibernate-core" % "6.4.4.Final",

	// JDBC Driver
	"com.mysql" % "mysql-connector-j" % "8.3.0",

	// Testing
	"org.junit.jupiter" % "junit-jupiter-api" % "5.10.0" % Test,
	"org.junit.jupiter" % "junit-jupiter-engine" % "5.10.0" % Test,
	"org.junit.jupiter" % "junit-jupiter-params" % "5.10.0" % Test

)


// Για να τρέχει το JUnit 5 με sbt
testFrameworks += new TestFramework("org.junit.platform.sbt.JUnitPlatformFramework")






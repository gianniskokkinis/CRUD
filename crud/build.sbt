name := """CRUD"""
organization := "synergic"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.16"

libraryDependencies ++= Seq(
	guice,

	javaJdbc,

	// Jakarta Persistence API
	"jakarta.persistence" % "jakarta.persistence-api" % "3.1.0",

	// Hibernate ORM
	"org.hibernate.orm" % "hibernate-core" % "6.4.4.Final",

	// JDBC Driver
	"com.mysql" % "mysql-connector-j" % "8.3.0",

	"org.apache.commons" % "commons-dbcp2" % "2.9.0",

	"com.typesafe" % "config" % "1.4.2",


	// Testing
	"org.junit.jupiter" % "junit-jupiter-api" % "5.10.0" % Test,
	"org.junit.jupiter" % "junit-jupiter-engine" % "5.10.0" % Test,
	"org.junit.jupiter" % "junit-jupiter-params" % "5.10.0" % Test,
	"org.junit.platform" % "junit-platform-launcher" % "1.10.0" % Test,
	"net.aichler" % "jupiter-interface" % "0.11.1" % Test

)


// Test configuration
Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-s")

// Verbose test output
Test / logBuffered := false





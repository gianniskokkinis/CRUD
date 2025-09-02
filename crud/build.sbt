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
	"com.mysql" % "mysql-connector-j" % "8.3.0"


)






name := """KielAhoi"""

version := "2.6.x"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies += guice
libraryDependencies += "org.webjars" % "bootstrap" % "4.0.0-beta-1"
libraryDependencies += "org.webjars" % "jquery" % "3.2.1"
libraryDependencies += "org.webjars.npm" % "popper.js" % "1.12.5"
libraryDependencies += "org.webjars.bower" % "leaflet" % "1.2.0"

// lb: Datenbank
libraryDependencies += javaJdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
libraryDependencies += javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api")
// alt: libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "4.3.4.Final"

libraryDependencies ++= Seq(javaJpa,"org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final") // replace by your jpa implementation

// PlayKeys.externalizeResources := false // beim Deployen aktivieren


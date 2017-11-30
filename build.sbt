name := """kielahoi"""

version := "2.6.x"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies += guice
libraryDependencies += "org.webjars" % "bootstrap" % "4.0.0-beta-1"
libraryDependencies += "org.webjars" % "jquery" % "3.2.1"
libraryDependencies += "org.webjars.npm" % "popper.js" % "1.12.5"
libraryDependencies += "org.webjars.bower" % "leaflet" % "1.2.0"

// Datenbank
libraryDependencies += javaJpa
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"

// Pegel auslesen
libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"

// Tiefgang einlesen
libraryDependencies += javaForms


// siehe https://www.playframework.com/documentation/2.6.x/JavaJPA
PlayKeys.externalizeResources := false // beim Deployen aktivieren
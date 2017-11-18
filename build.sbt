name := """KielAhoi"""

version := "2.6.x"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies += guice
libraryDependencies += "org.webjars" % "bootstrap" % "4.0.0-beta-1"
libraryDependencies += "org.webjars" % "jquery" % "3.2.1"
libraryDependencies += "org.webjars.npm" % "popper.js" % "1.12.5"
libraryDependencies += "org.webjars.bower" % "leaflet" % "1.2.0"


// lb: Datenbank
libraryDependencies += javaJpa
//libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.5.Final"










// PlayKeys.externalizeResources := false // beim Deployen aktivieren


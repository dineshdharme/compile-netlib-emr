

name := "compile-netlib-emr"
version := "0.1.0"

scalaVersion := "2.12.14"
val sparkVersion = "3.4.1"


lazy val compiledJarsDirectory = settingKey[File]("The directory for compiled jars")
lazy val blasJar = settingKey[File]("The directory for compiled jars")
lazy val arpackJar = settingKey[File]("The directory for compiled jars")
lazy val lapackJar = settingKey[File]("The directory for compiled jars")

ThisBuild / compiledJarsDirectory := baseDirectory.value / "staginglib"
ThisBuild / blasJar  := (ThisBuild / compiledJarsDirectory ).value / "blas-6.6.6.jar"
ThisBuild / arpackJar  := (ThisBuild / compiledJarsDirectory ).value / "arpack-6.6.6.jar"
ThisBuild / lapackJar  := (ThisBuild / compiledJarsDirectory ).value / "lapack-6.6.6.jar"


libraryDependencies ++= Seq(
  "dev.ludovic.netlib" % "blas" % "6.6.6" from blasJar.value.toURI.toString,
  "dev.ludovic.netlib" % "arpack" % "6.6.6" from arpackJar.value.toURI.toString,
  "dev.ludovic.netlib" % "lapack" % "6.6.6" from lapackJar.value.toURI.toString,
  "org.scalanlp" %% "breeze" % "2.1.0",
  "org.scalanlp" %% "breeze-natives" % "2.1.0",

)




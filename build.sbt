

name := "compile-netlib-emr"
version := "0.1.0"

scalaVersion := "2.12.14"
val sparkVersion = "3.5.0"


lazy val compiledJarsDirectory = settingKey[File]("The directory for compiled jars")
lazy val blasJar = settingKey[File]("The directory for compiled jars")
lazy val arpackJar = settingKey[File]("The directory for compiled jars")
lazy val lapackJar = settingKey[File]("The directory for compiled jars")

ThisBuild / compiledJarsDirectory := baseDirectory.value / "staginglib"
ThisBuild / blasJar  := (ThisBuild / compiledJarsDirectory ).value / "blas-6.6.6.jar"
ThisBuild / arpackJar  := (ThisBuild / compiledJarsDirectory ).value / "arpack-6.6.6.jar"
ThisBuild / lapackJar  := (ThisBuild / compiledJarsDirectory ).value / "lapack-6.6.6.jar"


libraryDependencies ++= Seq(
  //"dev.ludovic.netlib" % "blas" % "6.6.6" from blasJar.value.toURI.toString,
  //"dev.ludovic.netlib" % "arpack" % "6.6.6" from arpackJar.value.toURI.toString,
  //"dev.ludovic.netlib" % "lapack" % "6.6.6" from lapackJar.value.toURI.toString,

  "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
  //"org.apache.commons" % "commons-math3" % "3.6.1",

  //"dev.ludovic.netlib" % "blas" % "3.0.3",
  //"dev.ludovic.netlib" % "lapack" % "3.0.3",

  //"org.scalanlp" %% "breeze-macros" % "2.1.0-SNAPSHOT" from "file:///mnt/684C285C4C2826F2/Freelancing/breeze-single-precision-support/macros/target/scala-2.12/breeze-macros_2.12-2.1.0-SNAPSHOT.jar",
 //"org.scalanlp" %% "breeze" % "2.1.0-SNAPSHOT" from "file:///mnt/684C285C4C2826F2/Freelancing/breeze-single-precision-support/math/target/scala-2.12/breeze_2.12-2.1.0-SNAPSHOT.jar"

  "org.scalanlp" %% "breeze" % "2.1.0",
 "org.scalanlp" %% "breeze-macros" % "2.1.0",


)




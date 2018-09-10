import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
import play.core.PlayVersion.{current => playVersion}

name := """daf-configurator"""

version := "1.0.0-SNAPSHOT"

val isStaging = System.getProperty("STAGING") != null

lazy val root = (project in file(".")).enablePlugins(PlayJava, DockerPlugin)

scalaVersion := "2.12.2"


val AkkaVersion = "2.5.3"

val playLibraries = Seq(
  "org.pac4j"          %% "play-pac4j"       % "6.0.0",
  "org.pac4j"          % "pac4j-http"       % "3.0.0",
  "org.pac4j"          % "pac4j-jwt"        % "3.0.0" exclude("commons-io", "commons-io"),
  "org.pac4j"          % "pac4j-ldap"       % "3.0.0",
  "com.lightbend.play" %% "play-socket-io" % "1.0.0-beta-2",
  "com.typesafe.play" %% "play" % playVersion,
  "com.typesafe.akka" %% "akka-remote" % AkkaVersion
)

libraryDependencies += "org.webjars" % "swagger-ui" % "3.1.5"
libraryDependencies += "javax.validation" % "validation-api" % "1.1.0.Final"
//libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.3.0"
libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "0.22"

libraryDependencies += guice

libraryDependencies ++= playLibraries


val sopts = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Xfuture",
  "-Xlint",
  "-no-java-comments"
)
val soptsNoTest = Seq(
  "-Ywarn-dead-code",
  "-Ywarn-value-discard"
)

scalacOptions in (Compile, doc) ++= sopts ++ soptsNoTest
scalacOptions in Test ++= sopts


//dockerBaseImage := "anapsix/alpine-java:8_jdk_unlimited"
dockerBaseImage := "openjdk:8u171-jdk-slim"

//dockerCommands := dockerCommands.value.flatMap {
//  case cmd@Cmd("FROM", _) => List(cmd,
//    Cmd("RUN", "apk update && apk add bash krb5-libs krb5"),
//    Cmd("RUN", "ln -sf /etc/krb5.conf /opt/jdk/jre/lib/security/krb5.conf")
//  )
//  case other => List(other)
//}

dockerExposedPorts := Seq(9000)

dockerEntrypoint := {Seq(s"bin/${name.value}", "-Dconfig.file=conf/application.conf")}

dockerRepository := { if(isStaging)Option("nexus.teamdigitale.test") else Option("nexus.daf.teamdigitale.it") }


publishTo in ThisBuild := {
  val nexus = if(isStaging) "http://nexus.teamdigitale.test:8081/repository/"
  else "http://nexus.daf.teamdigitale.it:8081/repository/"

  if (isSnapshot.value)
    Some("snapshots" at nexus + "maven-snapshots/")
  else
    Some("releases"  at nexus + "maven-releases/")
}

credentials += {if(isStaging) Credentials(Path.userHome / ".ivy2" / ".credentialsTest") else Credentials(Path.userHome / ".ivy2" / ".credentials")}



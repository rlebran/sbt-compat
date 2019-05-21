val sbtcompat = project in file(".")

organization in ThisBuild := "com.dwijnand"
        name              := "sbt-compat"
    licenses in ThisBuild := Seq(("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0")))
 description in ThisBuild := "A compatibility library; backports parts of sbt 1's public API in sbt 0.13"
  developers in ThisBuild := List(Developer("dwijnand", "Dale Wijnand", "dale wijnand gmail com", url("https://dwijnand.com")))
   startYear in ThisBuild := Some(2017)

       sbtPlugin           := true
      sbtVersion in Global := "1.2.8"

scalaVersion in ThisBuild := "2.12.8"

scalacOptions in ThisBuild ++= Seq("-encoding", "utf8")
scalacOptions in ThisBuild ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
scalacOptions in ThisBuild  += "-Xfuture"
scalacOptions in ThisBuild  += "-Yno-adapted-args"
scalacOptions in ThisBuild  += "-Ywarn-dead-code"
scalacOptions in ThisBuild  += "-Ywarn-numeric-widen"
scalacOptions in ThisBuild  += "-Ywarn-value-discard"


import com.typesafe.tools.mima.core._, ProblemFilters._
mimaPreviousArtifacts := Set {
  val m = organization.value %% moduleName.value % "1.0.0-M1"
  val sbtBinV = (sbtBinaryVersion in pluginCrossBuild).value
  val scalaBinV = (scalaBinaryVersion in update).value
  if (sbtPlugin.value)
    Defaults.sbtPluginExtra(m cross CrossVersion.Disabled, sbtBinV, scalaBinV)
  else
    m
}
mimaBinaryIssueFilters ++= Seq()

cancelable in Global := true

publishMavenStyle := true
publishTo := {
  val nexus = "https://repo.powerspace.com/artifactory/"
  if (Keys.isSnapshot.value)
    Some("snapshots" at nexus + "sbt-snapshot-local")
  else
    Some("releases" at nexus + "sbt-release-local")
}
credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

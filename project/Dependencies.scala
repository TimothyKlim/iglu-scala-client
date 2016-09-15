/*
 * Copyright (c) 2014 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    "Twitter Maven Repo" at "http://maven.twttr.com/" // For Twitter's util functions
  )

  object V {
    // Java
    val commonsLang = "3.4"
    val jacksonDatabind = "2.8.2"
    val jsonValidator = "2.2.6"
    // Scala
    val json4s = "3.5.0-SNAPSHOT"
    val scalaz = "7.2.6"
    val collUtils = "6.37.0"
    // Scala (test only)
    val specs2 = "3.8.5"
    val scalazSpecs2 = "0.4.0"
  }

  object Libraries {
    // Java
    val commonsLang = "org.apache.commons" % "commons-lang3" % V.commonsLang
    val jacksonDatabind = "com.fasterxml.jackson.core" % "jackson-databind" % V.jacksonDatabind
    val jsonValidator = "com.github.fge" % "json-schema-validator" % V.jsonValidator
    // Scala
    val json4sJackson = "org.json4s" %% "json4s-jackson" % V.json4s
    val json4sScalaz = "org.json4s" %% "json4s-scalaz" % V.json4s
    val scalaz = "org.scalaz" %% "scalaz-core" % V.scalaz
    val collUtils = "com.twitter" %% "util-collection" % V.collUtils
    // Scala (test only)
    val specs2 = "org.specs2" %% "specs2-core" % V.specs2 % "test"
    val specs2Mock = "org.specs2" %% "specs2-mock" % V.specs2
    val scalazSpecs2 = "org.typelevel" %% "scalaz-specs2" % V.scalazSpecs2 % "test"
  }
}

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
package com.snowplowanalytics.iglu.client
package repositories

// Jackson
import com.fasterxml.jackson.databind.JsonNode

// Scalaz
import scalaz._
import Scalaz._

// json4s
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

object RepositoryRefConfig {

  /**
   */
  def apply(ref: JsonNode): Validated[RepositoryRefConfig] =
    apply(fromJsonNode(ref))

  /**
   */
  implicit val formats = DefaultFormats
  def apply(ref: JValue): Validated[RepositoryRefConfig] =
    ref.extract[RepositoryRefConfig].success
}

/**
 * Common config for RepositoryRef classes.
 */
case class RepositoryRefConfig(
  name: String,
  instancePriority: Int,
  vendorPrefixes: List[String]
  )

/**
 * Common parent of all RepositoryRef classes.
 *
 * @param name The name of this repository
 * @param instancePriority The priority of this
 *        reference instance
 * @param vendorPrefixes The list (possibly
 *        empty) of schema vendors, or prefixes
 *        of schema vendors, to 
 */
abstract class RepositoryRef(
  val config: RepositoryRefConfig) extends Lookup {

  /**
   * Abstract method. All repositories with a
   * search priority of 1 will be checked before
   * any repository with a search priority of 2.
   *
   * @return the search priority for this group
   *         aka class of repository references
   */
  def groupPriority: Int

  /**
   * Helper to check if this repository should take
   * priority because of a vendor prefix match. Returns
   * true if we matched our schema's vendor in the
   * list of vendor prefixes.
   *
   * @param schemaKey The SchemaKey uniquely identifying
   *        the schema in Iglu. We will use the vendor
   *        within the SchemaKey to match against our
   *        prefixes
   * @return whether this is a priority lookup or
   *         not
   */
  def vendorMatched(schemaKey: SchemaKey): Boolean = {
    val matches = for {
      p <- config.vendorPrefixes
      m = schemaKey.vendor.startsWith(p)
    } yield m
    matches.foldLeft(false)(_ || _) // True if any match
  }
}

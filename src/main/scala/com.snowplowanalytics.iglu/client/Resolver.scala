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

// Jackson
import com.fasterxml.jackson.databind.JsonNode

// LRU
// import com.twitter.util.LruMap

// Scalaz
import scalaz._
import Scalaz._

// This project
import repositories.RepositoryRef

/** How to handle schema resolution */
sealed trait ResolutionMode
/** Return failure if schema not found */
object PessimisticResolution extends ResolutionMode
/** Return identity schema if schema not found */
object OptimisticResolution extends ResolutionMode

/**
 * Resolves schemas from one or more Iglu schema
 * repositories.
 *
 * This is an extremely primitive implementation.
 * Currently it only supports lookups of schemas
 * specified by the exact same version (i.e.
 * MODEL-REVISION-ADDITION).
 */
class Resolver(
  repos: RepositoryRefNel,
  mode: ResolutionMode,
  lruCache: Int = 500) {
  
  // Initialise the cache
  private val lru: MaybeSchemaLruMap = if (lruCache > 0) Some(new SchemaLruMap(lruCache)) else None

  /**
   * Re-sorts our Nel of RepositoryRefs into the
   * optimal order for querying.
   *
   * @param 
   * @return
   */
  def prioritizeRepos(schemaKey: SchemaKey): RepositoryRefNel =
    repos // TODO: implement this
}

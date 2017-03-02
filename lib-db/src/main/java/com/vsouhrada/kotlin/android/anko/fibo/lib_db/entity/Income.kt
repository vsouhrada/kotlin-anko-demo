package com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity

import io.requery.*
import java.sql.Timestamp

/**
 * @author vsouhrada
 * @since 0.1.0
 * @see[Persistable]
 */
@Entity
interface Income : Persistable {

  @get:Key
  @get:Generated
  var id: Int

  @get:Column(nullable = false)
  var creationTimestamp: Timestamp

  @get:Column(nullable = false)
  var amount: Double

  @get:Column(nullable = false, length = 255)
  var description: String

  // TODO


}
package com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity

import io.requery.Column
import io.requery.Generated
import io.requery.Key
import io.requery.Persistable

/**
 * @author vsouhrada
 * @since 0.1.0
 * @see[Persistable]
 */
//@Entity
interface Payment : Persistable {

  @get:Key
  @get:Generated
  var id: Int

  @get:Column(length = 60, nullable = false)
  var name: String

  // TODO prefered language

}
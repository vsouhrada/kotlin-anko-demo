package com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity

import io.requery.Column
import io.requery.Generated
import io.requery.Key
import io.requery.Persistable

/**
 * @author vsouhrada
 * @see[Persistable]
 * @version 0.1
 * @since 0.1
 */
//@Entity
interface Category : Persistable {

  @get:Key
  @get:Generated
  var id: Int

  @get:Column(nullable = false)
  var parentId: Int

  @get:Column(length = 40, nullable = false)
  var name: String

  @get:Column(length = 255)
  var description: String

  // TODO language
  // TODO categoryType
}
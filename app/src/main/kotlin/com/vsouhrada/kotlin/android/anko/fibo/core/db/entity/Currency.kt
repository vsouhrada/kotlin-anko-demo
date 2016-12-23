package com.vsouhrada.kotlin.android.anko.fibo.core.db.entity

import io.requery.Column
import io.requery.Entity
import io.requery.Key
import io.requery.Persistable

/**
 * @author vsouhrada
 * @version 0.1.0
 * @since 0.1.0
 */
@Entity
interface Currency : Persistable {

    @get:Key
    var code: String

    @get:Column(length = 20, unique = true, nullable = false)
    var name: String

    @get:Column(length = 10, nullable = false)
    var symbol: String

//    @get:OneToMany
//    var accounts: List<Account>
}
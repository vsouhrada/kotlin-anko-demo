package com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity

import io.requery.*

/**
 * @author vsouhrada
 * @see[Persistable]
 * @version 0.1
 * @since 0.1
 */
@Entity
interface User : Persistable {

    @get:Key
    @get:Generated
    var id: Int

    @get:Column(length = 20, unique = true, nullable = false)
    var userName: String

    @get:Column(length = 20, nullable = false)
    var password: String

    @get:Column(length = 20)
    var firstName: String

    @get:Column(length = 20)
    var lastName: String

    @get:Column(length = 30)
    var email: String

    @get:OneToMany
    val userAccounts: List<UserAccount>
}
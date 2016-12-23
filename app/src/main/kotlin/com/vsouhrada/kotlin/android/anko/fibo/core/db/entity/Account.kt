package com.vsouhrada.kotlin.android.anko.fibo.core.db.entity

import io.requery.*

/**
 * @author vsouhrada
 * @see[Persistable]
 * @version 0.1
 * @since 0.1
 */
@Entity
interface Account : Persistable {

    @get:Key
    @get:Generated
    var id: Int

    @get:Column(length = 15, nullable = false)
    var name: String
    @get:Column(length = 255)
    var description: String

    @get:ForeignKey
    @get:ManyToOne
    val currency: Currency

    @get:Column(length = 30, nullable = false)
    var balance: Double

    @get:OneToMany
    val userAccounts: List<UserAccount>

}
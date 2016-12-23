package com.vsouhrada.kotlin.android.anko.fibo.core.db.entity

import android.os.Parcelable
import io.requery.*

/**
 * @author vsouhrada
 * @see[Persistable]
 * @version 0.1
 * @since 0.1
 */
@Entity
interface UserAccount : Parcelable, Persistable {

    @get:Key
    @get:ForeignKey
    @get:ManyToOne
    var user: User

    @get:ForeignKey
    @get:ManyToOne
    var account: Account
}
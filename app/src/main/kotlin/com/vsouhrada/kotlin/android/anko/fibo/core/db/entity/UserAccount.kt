package com.vsouhrada.kotlin.android.anko.fibo.core.db.entity

import android.os.Parcelable
import io.requery.ForeignKey
import io.requery.Key
import io.requery.ManyToOne
import io.requery.Persistable

/**
 * @author vsouhrada
 * @see[Persistable]
 * @version 0.1
 * @since 0.1
 */
//@Entity
interface UserAccount : Parcelable, Persistable {

    @get:Key
    @get:ForeignKey
    @get:ManyToOne
    var user: User

    @get:ForeignKey
    @get:ManyToOne
    var account: Account
}
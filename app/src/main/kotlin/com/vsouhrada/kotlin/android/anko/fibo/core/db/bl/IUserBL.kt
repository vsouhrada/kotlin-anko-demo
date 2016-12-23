package com.vsouhrada.apps.fibo.core.db.bl

import com.vsouhrada.kotlin.android.anko.fibo.core.model.UserDO

/**
 * @author vsouhrada
 * @version 0.1
 * @since 0.1
 */
interface IUserBL {

    fun existUser() : Boolean

    fun saveUser(userDO: UserDO)
}
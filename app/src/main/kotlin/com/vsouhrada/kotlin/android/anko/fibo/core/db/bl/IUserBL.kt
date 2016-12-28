package com.vsouhrada.apps.fibo.core.db.bl

import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials

/**
 * @author vsouhrada
 * @version 0.1
 * @since 0.1
 */
interface IUserBL {

  fun existUser(): Boolean

  fun saveUser(userDO: UserDO)

  fun getUser(): UserDO

  fun getUser(credentials: AuthCredentials): UserDO?
}
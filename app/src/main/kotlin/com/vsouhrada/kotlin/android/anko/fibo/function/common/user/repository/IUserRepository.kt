package com.vsouhrada.kotlin.android.anko.fibo.function.common.user.repository

import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials

/**
 * @author vsouhrada
 * @since 0.1.0
 */
interface IUserRepository {

  fun existUser(): Boolean

  fun insertUser(credentials: AuthCredentials)

  fun findUserById(id: Int): UserDO?

  fun loadUser(credentials: AuthCredentials): UserDO?
}
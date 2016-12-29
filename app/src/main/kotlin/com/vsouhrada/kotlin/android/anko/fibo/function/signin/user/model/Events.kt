package com.vsouhrada.apps.fibo.function.user

import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials

/**
 *
 * @author vsouhrada
 * @property[userDO] User Domain Object
 * @since 0.1.0
 */
data class CreateUserEvent(val credentials: AuthCredentials)
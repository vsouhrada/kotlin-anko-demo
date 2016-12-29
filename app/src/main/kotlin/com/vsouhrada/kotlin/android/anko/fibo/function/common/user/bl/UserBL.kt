package com.vsouhrada.kotlin.android.anko.fibo.function.common.user.bl

import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO
import com.vsouhrada.kotlin.android.anko.fibo.function.common.user.repository.IUserRepository
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials
import com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity.UserEntity
import io.requery.Persistable
import io.requery.sql.KotlinEntityDataStore
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * @author vsouhrada
 * @version 0.1
 * @since 0.1
 * @see[IUserBL]
 */
class UserBL @Inject constructor(val repository: IUserRepository) : IUserBL {

  override fun existUser(): Boolean {
    return repository.existUser()
  }

  override fun saveUser(credentials: AuthCredentials) {
    repository.insertUser(credentials)
  }

  override fun getUser(credentials: AuthCredentials): UserDO? = repository.loadUser(credentials)

  override fun getUserById(id: Int): UserDO? = repository.findUserById(id)

}
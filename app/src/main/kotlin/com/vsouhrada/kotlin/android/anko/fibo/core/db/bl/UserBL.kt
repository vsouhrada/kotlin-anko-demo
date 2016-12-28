package com.vsouhrada.kotlin.android.anko.fibo.core.db.bl

import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO
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
class UserBL @Inject constructor(val dataStore: KotlinEntityDataStore<Persistable>) : IUserBL {

  override fun existUser(): Boolean {
    return dataStore.count(UserEntity::class).get().value() > 0
  }

  override fun saveUser(userDO: UserDO) {
    val user = UserEntity()
    with(user) {
      userName = userDO.username
      password = userDO.password
    }

    doAsync {
      dataStore.insert(user)
    }
  }

  override fun getUser(): UserDO {
    val result = dataStore.select(UserEntity::class).get().first()

    return convertToDO(result)
  }

  override fun getUser(credentials: AuthCredentials): UserDO? {
    var userDO: UserDO? = null
    dataStore.invoke {
      val selectResult =
        select(UserEntity::class) where (
                UserEntity.USER_NAME.eq(credentials.userName).and(UserEntity.PASSWORD.eq(credentials.password)))

      selectResult.get().firstOrNull()?.let { userDO = convertToDO(it) }
    }

    return userDO
  }

  private fun convertToDO(entity: UserEntity): UserDO {
    return UserDO(username = entity.userName, password = entity.password)
  }

}
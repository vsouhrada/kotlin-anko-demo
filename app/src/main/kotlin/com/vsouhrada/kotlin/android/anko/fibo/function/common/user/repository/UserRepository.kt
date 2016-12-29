package com.vsouhrada.kotlin.android.anko.fibo.function.common.user.repository

import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials
import com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity.UserEntity
import io.requery.Persistable
import io.requery.sql.KotlinEntityDataStore
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * @author vsouhrada
 * @see[IUserRepository]
 * @since 0.1.0
 */
class UserRepository @Inject constructor(val dataStore: KotlinEntityDataStore<Persistable>) : IUserRepository {

  override fun existUser(): Boolean {
    return dataStore.count(UserEntity::class).get().value() > 0
  }

  override fun insertUser(credentials: AuthCredentials) {
    val user = UserEntity()
    with(user) {
      userName = credentials.userName
      password = credentials.password
    }

    doAsync {
      dataStore.insert(user)
    }
  }

  override fun findUserById(id: Int): UserDO? {
    var userDO: UserDO? = null
    dataStore.invoke {
      val selectResult = select(UserEntity::class) where (UserEntity.ID.eq(id))
      selectResult.get().firstOrNull()?.let { userDO = convertToDO(it) }
    }

    return userDO
  }

  override fun loadUser(credentials: AuthCredentials): UserDO? {
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
    return UserDO(id = entity.id, username = entity.userName, password = entity.password)
  }

}
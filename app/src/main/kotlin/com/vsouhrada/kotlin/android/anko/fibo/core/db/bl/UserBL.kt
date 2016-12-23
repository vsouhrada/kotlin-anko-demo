package com.vsouhrada.kotlin.android.anko.fibo.core.db.bl

import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.kotlin.android.anko.fibo.core.db.entity.User
import com.vsouhrada.kotlin.android.anko.fibo.core.db.entity.UserEntity
import com.vsouhrada.kotlin.android.anko.fibo.core.model.UserDO
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
        return dataStore.count(User::class).get().value() > 0
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

        return concertToDO(result)
    }

    private fun concertToDO(entity: UserEntity): UserDO {
        return UserDO(username = entity.userName, password = entity.password)
    }
}
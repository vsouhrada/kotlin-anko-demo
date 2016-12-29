package com.vsouhrada.kotlin.android.anko.fibo.app

import android.app.Application
import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.apps.fibo.injection.module.ApplicationModule
import com.vsouhrada.kotlin.android.anko.fibo.core.session.ISessionManager
import com.vsouhrada.kotlin.android.anko.fibo.core.session.model.UserInfoPrefModel
import com.vsouhrada.kotlin.android.anko.fibo.injection.component.ApplicationComponent
import com.vsouhrada.kotlin.android.anko.fibo.injection.component.DaggerApplicationComponent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

/**
 * @author vsouhrada
 * @since 0.1.0
 */
class FiboApp : Application(), AnkoLogger {

  @Inject lateinit var sessionManager: ISessionManager
  @Inject lateinit var userBL: IUserBL

  companion object {
    @JvmStatic lateinit var applicationComponent: ApplicationComponent
    private var instance: Application? = null
    fun instance() = instance!!
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    initDaggerComponent()

    val storedUserId = UserInfoPrefModel.userId
    if (storedUserId != -1) {
      val user = userBL.getUserById(UserInfoPrefModel.userId)
      if (user != null) {
        sessionManager.putUserOnSession(user)
      } else {
        error { "User with id=$storedUserId not found in database!!!" }
      }
    }
  }

  private fun initDaggerComponent() {
    applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule((this))).build()
    applicationComponent.inject(this)
  }

}
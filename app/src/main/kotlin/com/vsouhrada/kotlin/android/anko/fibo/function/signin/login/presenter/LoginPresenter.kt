package com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.presenter

import com.chibatching.kotpref.blockingBulk
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.kotlin.android.anko.fibo.function.drawer.DrawerActivity
import com.vsouhrada.kotlin.android.anko.fibo.core.session.ISessionManager
import com.vsouhrada.kotlin.android.anko.fibo.core.session.model.UserInfoPrefModel
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view.ILoginViewPresenter
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view.LoginView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import javax.inject.Inject

/**
 * @author vsouhrada
 * @since 0.1.0
 */
class LoginPresenter @Inject constructor(val userBL: IUserBL, val sessionManager: ISessionManager)
  : MvpBasePresenter<LoginView>(), ILoginViewPresenter {

  //private var subscriber: Subscriber<UserDO>? = null

  fun showLoginForm() {
    view?.showLoginForm()
  }

  override fun doLogin(credentials: AuthCredentials) {
    if (isViewAttached) {
      view?.showLoading()
    }

    //cancelSubscription()

    doAsync {
      val user = userBL.getUser(credentials)
      uiThread {
        if (isViewAttached) {
          if (user != null) {
            sessionManager.putUserOnSession(user)
            if (credentials.rememberMe) {
              UserInfoPrefModel.blockingBulk { userId = user.id }
            } else {
              UserInfoPrefModel.blockingBulk { userId = -1 }
            }
            view?.loginSuccessful()
            view?.ankoView?.startActivity<DrawerActivity>()
            view?.ankoView?.owner?.finish()
          } else {
            view?.showError()
          }
        }
      }
    }
  }

//  /**
//   * Cancels any previous callback
//   */
//  private fun cancelSubscription() {
//    if (subscriber != null && !subscriber.isUnsubscribed()) {
//      subscriber.unsubscribe()
//    }
//  }
//
//  override fun detachView(retainInstance: Boolean) {
//    super.detachView(retainInstance)
//    if (!retainInstance) {
//      cancelSubscription()
//    }
//  }

}
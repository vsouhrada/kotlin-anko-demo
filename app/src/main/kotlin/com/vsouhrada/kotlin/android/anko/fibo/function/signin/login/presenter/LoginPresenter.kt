package com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view.ILoginViewPresenter
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view.LoginView

/**
 * @author vsouhrada
 * @since 0.1.0
 */
class LoginPresenter(/*@Inject val userBL: IUserBL*/) : MvpBasePresenter<LoginView>(), ILoginViewPresenter {

  //private var subscriber: Subscriber<UserDO>? = null

  fun showLoginForm() {
    view?.showLoginForm()
  }

  override fun doLogin(credentials: AuthCredentials) {
    if (isViewAttached) {
      view?.showLoading()
    }

    //cancelSubscription()

    if (credentials.userName.equals("frosty") && credentials.password.equals("snow")) {
      view?.loginSuccessful()
    } else {
      view?.showError()
    }
//    doAsync {
//      val user = userBL.getUser(credentials)
//      uiThread {
//        if (isViewAttached) {
//          if (user != null) {
//            view?.loginSuccessful()
//          } else {
//            view?.showError()
//          }
//        }
//      }
//    }
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
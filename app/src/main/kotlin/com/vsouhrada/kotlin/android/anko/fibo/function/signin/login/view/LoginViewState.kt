package com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState

/**
 * @author vsouhrada
 * @see[ViewState]
 * @see[ILoginMvpView]
 * @since 0.1.0
 */
class LoginViewState : ViewState<ILoginView> {

  internal val STATE_SHOW_LOGIN_FORM = 0
  internal val STATE_SHOW_LOADING = 1
  internal val STATE_SHOW_ERROR = 2

  internal var state = STATE_SHOW_LOGIN_FORM

  override fun apply(view: ILoginView, retained: Boolean) {

    when (state) {
      STATE_SHOW_LOADING -> view.showLoading()

      STATE_SHOW_ERROR -> view.showError()

      STATE_SHOW_LOGIN_FORM -> view.showLoginForm()
    }
  }

  fun setShowLoginForm() {
    state = STATE_SHOW_LOGIN_FORM
  }

  fun setShowError() {
    state = STATE_SHOW_ERROR
  }

  fun setShowLoading() {
    state = STATE_SHOW_LOADING
  }
}

package com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view

import com.hannesdorfmann.mosby.mvp.MvpView
import com.vsouhrada.kotlin.android.anko.fibo.core.mvp.MvpAnkoView

/**
 * @author vsouhrada
 * @see[MvpView]
 * @since 0.1.0
 */
interface ILoginView : MvpAnkoView {

  fun showLoginForm()

  fun showError()

  fun showLoading()

  fun loginSuccessful()
}
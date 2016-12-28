package com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view

import com.hannesdorfmann.mosby.mvp.MvpView
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials

/**
 * @author vsouhrada
 * @since 0.1.0
 */
interface ILoginViewPresenter : MvpView{

  fun doLogin(credentials: AuthCredentials)

}
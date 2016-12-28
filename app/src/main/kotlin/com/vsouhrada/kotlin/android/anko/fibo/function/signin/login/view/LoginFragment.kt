package com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view

import android.os.Bundle
import android.view.View
import com.vsouhrada.kotlin.android.anko.fibo.core.mvp.BaseViewStateFragment
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.presenter.LoginPresenter
import org.jetbrains.anko.AnkoContext

/**
 * @author vsouhrada
 * @see[BaseViewStateFragment]
 * @see[ILoginMvpView]
 * @see[LoginPresenter]
 * @since 0.1.0
 */
class LoginFragment : BaseViewStateFragment<LoginView, LoginPresenter>() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isRetainInstance = true
  }

  override fun onCreateMvpAnkoView() = LoginView()

  override fun onCreateView(): View {
    view.viewState = viewState as LoginViewState

    return view.createView(AnkoContext.Companion.create(this.activity, this.activity))
  }

  override fun createPresenter(): LoginPresenter {
    val presenter = LoginPresenter()
    view.presenter = presenter

    return presenter
  }

  override fun createViewState() = LoginViewState()

  override fun onNewViewStateInstance() {
   presenter.showLoginForm()
  }

  interface Injector {

    fun inject(loginFragment: LoginFragment)

  }

}

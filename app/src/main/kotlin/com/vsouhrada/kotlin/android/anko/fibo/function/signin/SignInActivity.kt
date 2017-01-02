package com.vsouhrada.kotlin.android.anko.fibo.function.signin

import android.os.Bundle
import com.vsouhrada.apps.fibo.BaseActivity
import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.apps.fibo.function.user.CreateUserEvent
import com.vsouhrada.kotlin.android.anko.fibo.function.drawer.DrawerActivity
import com.vsouhrada.kotlin.android.anko.fibo.R
import com.vsouhrada.kotlin.android.anko.fibo.core.rx.RxBus
import com.vsouhrada.kotlin.android.anko.fibo.core.view.FragmentContainerView
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view.LoginFragment
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import rx.Subscription
import javax.inject.Inject

/**
 * @author vsouhrada
 * @see[BaseActivity]
 * @since 0.1.0
 */
class SignInActivity : BaseActivity() {

  @Inject lateinit var userBL: IUserBL
  @Inject lateinit var bus: RxBus
  private lateinit var subscription: Subscription

  override fun onResume() {
    super.onResume()
    subscription = bus.toObservable().subscribe { event ->
      if (event is CreateUserEvent) {
        userBL.saveUser(event.credentials)
        startActivity<DrawerActivity>()
        finish()
      }
    }
  }

  override fun onPause() {
    super.onPause()
    subscription?.unsubscribe()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // DI
    activityComponent().inject(this)
    // Create view
    FragmentContainerView().setContentView(this)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
              .replace(R.id.fragmentContainer, LoginFragment())
              .commit()
    }
  }

}

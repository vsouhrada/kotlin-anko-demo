package com.vsouhrada.kotlin.android.anko.fibo.function

import android.os.Bundle
import com.vsouhrada.apps.fibo.BaseActivity
import com.vsouhrada.kotlin.android.anko.fibo.DrawerActivity
import com.vsouhrada.kotlin.android.anko.fibo.core.session.ISessionManager
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * @author vsouhrada
 * @see[BaseActivity]
 * @version 0.1
 * @since 0.1
 */
class MainActivity : BaseActivity() {

  @Inject lateinit var sessionManager: ISessionManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // DI
    activityComponent().inject(this)

    if (sessionManager.checkLogin()) {
      //runOnUiThread { Toast.makeText(this, "NO User defined!!!", Toast.LENGTH_SHORT).show() }
      //startActivity(Intent(this, CreateUserActivity::class.java))
      startActivity<DrawerActivity>()
      finish()
    }

  }

}
package com.vsouhrada.kotlin.android.anko.fibo.function.user

import android.os.Bundle
import com.vsouhrada.apps.fibo.BaseActivity
import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.apps.fibo.function.user.CreateUserEvent
import com.vsouhrada.kotlin.android.anko.fibo.DrawerActivity
import com.vsouhrada.kotlin.android.anko.fibo.core.rx.RxBus
import com.vsouhrada.kotlin.android.anko.fibo.function.user.view.CreateUserView
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import rx.Subscription
import javax.inject.Inject

/**
 * @author vsouhrada
 * @see[BaseActivity]
 * @since 0.1.0
 */
class CreateUserActivity : BaseActivity() {

    @Inject lateinit var userBL: IUserBL
    @Inject lateinit var bus: RxBus
    private lateinit var subscription: Subscription

    override fun onResume() {
        super.onResume()
        subscription = bus.toObservable().subscribe { event ->
            if (event is CreateUserEvent) {
                userBL.saveUser(event.userDO)
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
        CreateUserView(bus).setContentView(this)
    }

}

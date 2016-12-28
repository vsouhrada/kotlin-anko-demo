package com.vsouhrada.kotlin.android.anko.fibo.injection.component

import com.vsouhrada.apps.fibo.injection.module.ActivityModule
import com.vsouhrada.apps.fibo.injection.qualifier.PerActivity
import com.vsouhrada.kotlin.android.anko.fibo.DrawerActivity
import com.vsouhrada.kotlin.android.anko.fibo.function.MainActivity
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.SignInActivity
import dagger.Component

/**
 * This component injects dependencies to all Activities across the application
 *
 * @author vsouhrada
 * @since 0.1.0
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class) )
interface ActivityComponent /*: LoginFragment.Injector*/ {

  fun inject(drawerActivity: DrawerActivity)

  fun inject(mainActivity: MainActivity)

  fun inject(signInActivity: SignInActivity)

}
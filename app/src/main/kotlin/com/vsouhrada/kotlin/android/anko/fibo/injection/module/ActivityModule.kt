package com.vsouhrada.apps.fibo.injection.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.vsouhrada.apps.fibo.injection.qualifier.ForActivity
import dagger.Module
import dagger.Provides

/**
 * @author vsouhrada
 * @since 0.1.0
 */
@Module
class ActivityModule(private val activity: Activity) {

  @Provides fun provideActivity(): Activity {
    return activity
  }

  @Provides @ForActivity fun providesContext(): Context {
    return activity
  }

  @Provides
  fun providesFragmentManager(): FragmentManager {
    return (activity as FragmentActivity).getSupportFragmentManager()
  }

//  @Provides @Singleton fun provideLoginPresenter(userBL: UserBL): LoginPresenter {
//    return LoginPresenter(userBL = userBL)
//  }

}
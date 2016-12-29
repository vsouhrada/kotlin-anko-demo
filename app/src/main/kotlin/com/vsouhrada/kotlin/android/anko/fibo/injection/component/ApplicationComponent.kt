package com.vsouhrada.kotlin.android.anko.fibo.injection.component

import android.app.Application
import android.content.Context
import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.apps.fibo.injection.module.ApplicationModule
import com.vsouhrada.apps.fibo.injection.qualifier.ForApplication
import com.vsouhrada.kotlin.android.anko.fibo.app.FiboApp
import com.vsouhrada.kotlin.android.anko.fibo.core.db.FiboDatabaseSource
import com.vsouhrada.kotlin.android.anko.fibo.core.rx.RxBus
import com.vsouhrada.kotlin.android.anko.fibo.core.session.ISessionManager
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.presenter.LoginPresenter
import dagger.Component
import io.requery.Persistable
import io.requery.sql.KotlinEntityDataStore
import javax.inject.Singleton

/**
 * @author vsouhrada
 * @since 0.1
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

  @ForApplication fun appContext(): Context

  fun application(): Application

  fun eventBus(): RxBus

  fun databaseSource(): FiboDatabaseSource

  fun dataStore(): KotlinEntityDataStore<Persistable>

  fun userBusinessLogic(): IUserBL

  fun sessionManager(): ISessionManager

  @Singleton fun loginPres(): LoginPresenter

  fun inject(application: FiboApp)
}
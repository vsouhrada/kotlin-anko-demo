package com.vsouhrada.apps.fibo.injection.module

import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.vsouhrada.apps.fibo.core.db.bl.IUserBL
import com.vsouhrada.apps.fibo.injection.qualifier.ForApplication
import com.vsouhrada.kotlin.android.anko.fibo.BuildConfig
import com.vsouhrada.kotlin.android.anko.fibo.core.db.FiboDatabaseSource
import com.vsouhrada.kotlin.android.anko.fibo.core.rx.RxBus
import com.vsouhrada.kotlin.android.anko.fibo.core.session.ISessionManager
import com.vsouhrada.kotlin.android.anko.fibo.core.session.SessionManager
import com.vsouhrada.kotlin.android.anko.fibo.function.common.user.bl.UserBL
import com.vsouhrada.kotlin.android.anko.fibo.function.common.user.repository.IUserRepository
import com.vsouhrada.kotlin.android.anko.fibo.function.common.user.repository.UserRepository
import com.vsouhrada.kotlin.android.anko.fibo.function.income.bl.IIncomeBL
import com.vsouhrada.kotlin.android.anko.fibo.function.income.bl.IncomeBL
import com.vsouhrada.kotlin.android.anko.fibo.function.income.presenter.IncomePresenter
import com.vsouhrada.kotlin.android.anko.fibo.function.income.repository.IIncomeRepository
import com.vsouhrada.kotlin.android.anko.fibo.function.income.repository.IncomeRepository
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.presenter.LoginPresenter
import com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity.Models
import dagger.Module
import dagger.Provides
import io.requery.Persistable
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.TableCreationMode
import javax.inject.Singleton

/**
 * A module for Android-specific dependencies which require a [android.content.Context] or [ ] to create.
 *
 * @author vsouhrada
 * @since 0.1.0
 */
@Module
class ApplicationModule(private val application: Application) {

  @Provides
  @Singleton
  fun provideApplication(): Application {
    return application
  }

  /**
   * Allow the application context to be injected but require that it be annotated
   * with [ ][ForApplication] to explicitly differentiate it from an activity context.
   */
  @Provides
  @Singleton
  @ForApplication
  fun provideApplicationContext(): Context {
    return application
  }

  @Provides
  @Singleton
  fun provideEventBus(): RxBus {
    return RxBus()
  }

  @Provides
  @Singleton
  fun provideLocationManager(): LocationManager {
    return application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
  }

  @Provides
  @Singleton
  fun provideDatabaseSource(@ForApplication context: Context): FiboDatabaseSource {
    return FiboDatabaseSource(context = context, model = Models.DEFAULT, version = 1)
  }

  @Provides
  @Singleton
  fun provideDataStore(databaseSource: FiboDatabaseSource): KotlinEntityDataStore<Persistable> {
    if (BuildConfig.DEBUG) {
      databaseSource.setTableCreationMode(TableCreationMode.DROP_CREATE)
    }
    var tableCreationMode = TableCreationMode.CREATE_NOT_EXISTS
    if (BuildConfig.DEBUG) {
      tableCreationMode = TableCreationMode.DROP_CREATE
      // TODO handle with tableCreationMode
    }

    val dataStore = KotlinEntityDataStore<Persistable>(databaseSource.configuration)

    databaseSource.dataStore = dataStore

    return dataStore
  }

  @Provides
  @Singleton
  fun provideUserBusinessLogic(userRepository: IUserRepository): IUserBL {
    return UserBL(userRepository)
  }

  @Provides @Singleton fun provideUserRepository(dataStore: KotlinEntityDataStore<Persistable>): IUserRepository {
    return UserRepository(dataStore)
  }

  @Provides @Singleton fun provideSessionManager(application: Application): ISessionManager = SessionManager(application)

  @Provides @Singleton fun provideLoginPresenter(userBL: IUserBL, sessionManager: ISessionManager): LoginPresenter {
    return LoginPresenter(userBL, sessionManager)
  }

  @Provides @Singleton fun provideIncomePresenter(): IncomePresenter {
    return IncomePresenter()
  }

  @Provides @Singleton fun provideIncomeRepository(): IIncomeRepository {
    return IncomeRepository()
  }

  @Provides @Singleton fun provideIncomeBusinessLogic(repository: IIncomeRepository): IIncomeBL {
    return IncomeBL(repository)
  }
}
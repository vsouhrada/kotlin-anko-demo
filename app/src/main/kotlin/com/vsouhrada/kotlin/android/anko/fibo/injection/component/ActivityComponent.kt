package com.vsouhrada.kotlin.android.anko.fibo.injection.component

import com.vsouhrada.apps.fibo.injection.module.ActivityModule
import com.vsouhrada.apps.fibo.injection.qualifier.PerActivity
import com.vsouhrada.kotlin.android.anko.fibo.core.admin.db.AndroidDatabaseManager
import com.vsouhrada.kotlin.android.anko.fibo.function.MainActivity
import com.vsouhrada.kotlin.android.anko.fibo.function.user.CreateUserActivity
import dagger.Component

/**
 * This component injects dependencies to all Activities across the application
 *
 * @author vsouhrada
 * @version 0.1
 * @since 0.1
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(dbManager: AndroidDatabaseManager)

    fun inject(mainActivity: MainActivity)
    fun inject(createUserActivity: CreateUserActivity)

}
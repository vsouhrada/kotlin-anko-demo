package com.vsouhrada.kotlin.android.anko.fibo.app

import android.app.Application
import com.vsouhrada.apps.fibo.injection.module.ApplicationModule
import com.vsouhrada.kotlin.android.anko.fibo.injection.component.ApplicationComponent
import com.vsouhrada.kotlin.android.anko.fibo.injection.component.DaggerApplicationComponent

/**
 * @author vsouhrada
 * @since 0.1.0
 */
class FiboApp : Application() {

    companion object {
        @JvmStatic lateinit var applicationComponent: ApplicationComponent
        private var instance: Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule((this))).build()
        applicationComponent.inject(this)
    }

}
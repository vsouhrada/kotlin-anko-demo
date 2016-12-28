package com.vsouhrada.apps.fibo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vsouhrada.apps.fibo.injection.module.ActivityModule
import com.vsouhrada.kotlin.android.anko.fibo.app.FiboApp
import com.vsouhrada.kotlin.android.anko.fibo.injection.component.ActivityComponent
import com.vsouhrada.kotlin.android.anko.fibo.injection.component.DaggerActivityComponent
import icepick.Icepick

/**
 * @author vsouhrada
 * @version 0.1
 * @since 0.1
 * @see[AppCompatActivity]
 */
open class BaseActivity : AppCompatActivity() {

  private val activityComponent: ActivityComponent by lazy { activityComponent() }

  companion object {
    val IMAGE_TRANSITION_NAME = "activity_image_transition"
  }

  //val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

  //    fun onCreate(savedInstanceState: Bundle?, layoutResID: Int) {
//        super.onCreate(savedInstanceState)
//        setContentView(layoutResID)
//
//        setSupportActionBar(toolbar)
//    }
//
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Icepick.restoreInstanceState(this, savedInstanceState)
    //setSupportActionBar(toolbar)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    Icepick.saveInstanceState(this, outState)
  }

  fun activityComponent(): ActivityComponent {
    return DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent(FiboApp.applicationComponent).build()
  }
}
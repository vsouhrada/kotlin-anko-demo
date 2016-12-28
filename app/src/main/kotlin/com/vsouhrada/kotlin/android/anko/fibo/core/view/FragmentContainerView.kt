package com.vsouhrada.kotlin.android.anko.fibo.core.view

import com.vsouhrada.apps.fibo.BaseActivity
import com.vsouhrada.kotlin.android.anko.fibo.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent

/**
 * @author vsouhrada
 * @since 0.1.0
 */
open class FragmentContainerView : AnkoComponent<BaseActivity> {

  override fun createView(ui: AnkoContext<BaseActivity>) = with(ui) {
    frameLayout {
      id = R.id.fragmentContainer
      lparams {
        width = matchParent
        height = matchParent
      }
    }

  }
}
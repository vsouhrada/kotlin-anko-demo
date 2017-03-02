package com.vsouhrada.kotlin.android.anko.fibo.core.mvp

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateActivity
import icepick.Icepick
import java.lang.ref.WeakReference

/**
 *
 * @author vsouhrada
 * @since 0.1.0
 * @see[MvpAnkoView]
 * @see[MvpPresenter]
 * @see[MvpViewStateActivity]
 */
abstract class BaseViewStateActivity<V : MvpAnkoView, P : MvpPresenter<V>> : MvpViewStateActivity<V, P>() {

  protected var viewRef: WeakReference<V>

  init {
    viewRef = WeakReference(onCreateMvpAnkoView())
  }

  protected abstract fun onCreateMvpAnkoView(): V

  override fun getMvpView(): V {
    return viewRef.get()!!
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    Icepick.saveInstanceState(this, outState)
  }

  override fun setRestoringViewState(restoringViewState: Boolean) {
    super.setRestoringViewState(restoringViewState)
    viewRef.get()?.isRestoringViewState = true
  }

  override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {
    super.onViewStateInstanceRestored(instanceStateRetained)
    viewRef.get()?.isRestoringViewState = false
  }
}
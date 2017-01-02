/*
 *  Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.vsouhrada.kotlin.android.anko.fibo.core.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment
import icepick.Icepick

/**
 * @author vsouhrada
 * @since 0.1.0
 */
abstract class BaseViewStateFragment<V : MvpAnkoView, P : MvpPresenter<V>> : MvpViewStateFragment<V, P>() {

  protected lateinit var view: V

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //FragmentArgs.inject(this)
  }

  protected abstract fun onCreateView(): View

  protected abstract fun onCreateMvpAnkoView(): V

  override fun getMvpView(): V {
    return view
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    Icepick.restoreInstanceState(this, savedInstanceState)

    view = onCreateMvpAnkoView()

    return onCreateView()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    Icepick.saveInstanceState(this, outState)
  }

  override fun setRestoringViewState(restoringViewState: Boolean) {
    super.setRestoringViewState(restoringViewState)
    view.isRestoringViewState = true
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    view.isRestoringViewState = false
  }

}


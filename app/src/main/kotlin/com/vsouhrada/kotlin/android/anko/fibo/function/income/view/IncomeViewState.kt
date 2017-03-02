package com.vsouhrada.kotlin.android.anko.fibo.function.income.view

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState

/**
 * @author vsouhrada
 * @since 0.1.0
 * @see[ViewState]
 * @see[IIncomeView]
 */
class IncomeViewState : ViewState<IncomeView> {

  internal val STATE_SHOW_INCOME_FORM = 0
  internal val STATE_SHOW_ERROR = 1

  internal var state = STATE_SHOW_INCOME_FORM

  override fun apply(view: IncomeView, retained: Boolean) {
    when (state) {
      STATE_SHOW_INCOME_FORM -> view.showIncomeForm()
      STATE_SHOW_ERROR -> view.showError()
    }
  }

  fun setShowIncomeForm() {
    state = STATE_SHOW_INCOME_FORM
  }

  fun setShowError() {
    state = STATE_SHOW_ERROR
  }

}
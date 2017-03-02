package com.vsouhrada.kotlin.android.anko.fibo.function.income.view

import com.vsouhrada.kotlin.android.anko.fibo.core.mvp.MvpAnkoView

/**
 * @author vsouhrada
 * @since 0.1.0
 * @see[MvpAnkoView]
 */
interface IIncomeView : MvpAnkoView {

  fun showIncomeForm()

  fun showError()

}
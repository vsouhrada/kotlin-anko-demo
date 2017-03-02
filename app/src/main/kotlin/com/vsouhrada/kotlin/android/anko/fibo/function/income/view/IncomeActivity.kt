package com.vsouhrada.kotlin.android.anko.fibo.function.income.view

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import com.vsouhrada.kotlin.android.anko.fibo.R
import com.vsouhrada.kotlin.android.anko.fibo.app.FiboApp
import com.vsouhrada.kotlin.android.anko.fibo.core.mvp.BaseViewStateActivity
import com.vsouhrada.kotlin.android.anko.fibo.function.income.presenter.IncomePresenter
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

/**
 *
 * @author vsouhrada
 * @since 0.1.0
 * @see[BaseViewStateActivity]
 * @see[IncomeView]
 * @see[IncomePresenter]
 */
class IncomeActivity : BaseViewStateActivity<IncomeView, IncomePresenter>() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Create view by Anko
    mvpView.setContentView(this)

    setSupportActionBar(find<Toolbar>(R.id.toolbar))
    supportActionBar?.apply {
      setDisplayHomeAsUpEnabled(true)
      setDisplayUseLogoEnabled(true)
    }
  }

  override fun onCreateMvpAnkoView() = IncomeView()

  override fun createViewState(): ViewState<IncomeView> {
    return IncomeViewState()
  }

  override fun onNewViewStateInstance() {
    presenter.showIncomeForm()
  }

  override fun createPresenter(): IncomePresenter {
    val presenter = FiboApp.applicationComponent.incomePresenter()
    mvpView.presenter = presenter

    return presenter
  }

}
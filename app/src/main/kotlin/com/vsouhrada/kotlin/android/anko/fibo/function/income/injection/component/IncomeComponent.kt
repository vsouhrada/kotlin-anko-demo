package com.vsouhrada.kotlin.android.anko.fibo.function.income.injection.component

import com.vsouhrada.kotlin.android.anko.fibo.function.income.view.IncomeActivity

/**
 * @author vsouhrada
 * @since 0.1.0
 */
//@Singleton
//@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(IncomeModule::class))
interface IncomeComponent {

  //fun incomePresenter(): IncomePresenter

  fun inject(incomeActivity: IncomeActivity)


}
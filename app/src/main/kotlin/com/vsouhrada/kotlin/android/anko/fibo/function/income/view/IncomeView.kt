package com.vsouhrada.kotlin.android.anko.fibo.function.income.view

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.widget.LinearLayout
import com.vsouhrada.kotlin.android.anko.fibo.R
import com.vsouhrada.kotlin.android.anko.fibo.core.anko.attrAsDimen
import com.vsouhrada.kotlin.android.anko.fibo.function.income.presenter.IIncomePresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar

/**
 * @author vsouhrada
 * @since 0.1.0
 * @see[AnkoComponent]
 * @see[IIncomeView]
 */
class IncomeView : AnkoComponent<IncomeActivity>, IIncomeView {

  lateinit var presenter: IIncomePresenter

  lateinit var viewState: IncomeViewState

  override var isRestoringViewState = false

  lateinit var ankoView: AnkoContext<IncomeActivity>

  override fun createView(ui: AnkoContext<IncomeActivity>) = with(ui) {
    ankoView = ui
    linearLayout {
      lparams(width = matchParent, height = matchParent) {
        fitsSystemWindows = true
        orientation = LinearLayout.VERTICAL
      }

      toolbar {
        id = R.id.toolbar
        popupTheme = R.style.AppTheme_PopupOverlay
        backgroundResource = R.color.colorPrimary
        setTitleTextColor( ContextCompat.getColor(ctx, R.color.white))
        lparams(width = matchParent, height = ui.ctx.attrAsDimen(R.attr.actionBarSize))
      }

      relativeLayout {
        lparams {
          centerInParent()
        }

        val emptyView = textView("Say something outrageous.") {
          textSize = 16f
          typeface = Typeface.MONOSPACE
        }
      }
    }
  }

  override fun showIncomeForm() {
    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showError() {
    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
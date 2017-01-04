package com.vsouhrada.kotlin.android.anko.fibo.function.drawer.view

import android.support.constraint.ConstraintLayout
import android.support.v4.view.GravityCompat
import android.view.View
import com.github.clans.fab.FloatingActionMenu
import com.vsouhrada.kotlin.android.anko.fibo.R
import com.vsouhrada.kotlin.android.anko.fibo.core.anko.attrAsDimen
import com.vsouhrada.kotlin.android.anko.fibo.function.drawer.DrawerActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4._DrawerLayout
import org.jetbrains.anko.support.v4.drawerLayout

/**
 * @author vsouhrada
 * @since 0.1.0
 */
class DrawerView : AnkoComponent<DrawerActivity> {

  override fun createView(ui: AnkoContext<DrawerActivity>): View = with(ui) {
    drawerLayout {
      id = R.id.drawer_layout
      fitsSystemWindows = true
      lparams(width = matchParent, height = matchParent)
      createAppBar(ui)
      createNavigationView(ui)
    }

  }

  fun _DrawerLayout.createAppBar(ui: AnkoContext<DrawerActivity>) {
    coordinatorLayout {
      fitsSystemWindows = false
      lparams(width = matchParent, height = matchParent)

      appBarLayout(R.style.AppTheme_AppBarOverlay) {
        lparams(width = matchParent, height = wrapContent)

        toolbar {
          id = R.id.toolbar
          popupTheme = R.style.AppTheme_PopupOverlay
          backgroundResource = R.color.colorPrimary
          lparams(width = matchParent, height = ui.ctx.attrAsDimen(R.attr.actionBarSize))
        }
      }

      // https://github.com/Kotlin/anko/issues/210
      include<ConstraintLayout>(R.layout.content_drawer)
      // https://github.com/Kotlin/anko/issues/16
      include<FloatingActionMenu>(R.layout.fab_menu)
    }
  }

  fun _DrawerLayout.createNavigationView(ui: AnkoContext<DrawerActivity>) {
    navigationView {
      id = R.id.nav_view
      fitsSystemWindows = true
      lparams(height = matchParent, gravity = GravityCompat.START)
      setNavigationItemSelectedListener(ui.owner)
      inflateHeaderView(R.layout.nav_header_drawer)
      inflateMenu(R.menu.a_drawer_drawer)
    }
  }

}
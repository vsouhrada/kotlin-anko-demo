package com.vsouhrada.kotlin.android.anko.fibo

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.github.clans.fab.FloatingActionButton
import com.vsouhrada.apps.fibo.BaseActivity
import com.vsouhrada.kotlin.android.anko.fibo.core.session.ISessionManager
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import javax.inject.Inject

class DrawerActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

  @Inject lateinit var sessionManager: ISessionManager


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.a_drawer)

    activityComponent().inject(this)

    val toolbar = find<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    val fabNewIncome = find<FloatingActionButton>(R.id.fab_new_income)
    fabNewIncome.setOnClickListener { view ->
      Snackbar.make(view, getString(R.string.fab_main_new_income), Snackbar.LENGTH_SHORT)
              .setAction("Action", null).show()
    }

    val fabNewExpense = find<FloatingActionButton>(R.id.fab_new_expense)
    fabNewExpense.setOnClickListener { view ->
      Snackbar.make(view, getString(R.string.fab_main_new_expense), Snackbar.LENGTH_SHORT)
              .setAction("Action", null).show()
    }

    val fabBillReminder = find<FloatingActionButton>(R.id.fab_bill_reminder)
    fabBillReminder.setOnClickListener { view ->
      Snackbar.make(view, getString(R.string.fab_main_bill_reminder), Snackbar.LENGTH_SHORT)
              .setAction("Action", null).show()
    }

    val drawer = find<DrawerLayout>(R.id.drawer_layout)
    val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    drawer.setDrawerListener(toggle)
    toggle.syncState()

    val navigationView = find<NavigationView>(R.id.nav_view)
    navigationView.setNavigationItemSelectedListener(this)

    showUserInfo()
  }

  override fun onBackPressed() {
    val drawer = find<DrawerLayout>(R.id.drawer_layout)
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.drawer, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      R.id.nav_camera -> toast("Click Camera")
      R.id.nav_gallery -> toast("Click Gallery")
      R.id.nav_slideshow -> toast("Click Slideshow")
      R.id.nav_manage -> toast("Click Manage")
      R.id.nav_share -> toast("Click Share")
      R.id.nav_send -> toast("Click Send")
    }

    val drawer = find<DrawerLayout>(R.id.drawer_layout)
    drawer.closeDrawer(GravityCompat.START)

    return true
  }

  private fun showUserInfo() {
    val userNameView = find<TextView>(R.id.contentText)
    userNameView.text = sessionManager.username
  }
}

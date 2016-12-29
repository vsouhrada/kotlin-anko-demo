package com.vsouhrada.kotlin.android.anko.fibo.core.session

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.SignInActivity
import com.vsouhrada.kotlin.android.anko.fibo.utils.ACTION_LOGOUT
import com.vsouhrada.kotlin.android.anko.fibo.utils.EXTRA_PARAM_MSG
import javax.inject.Singleton


/**
 * @author vsouhrada
 * @see[ISessionManager]
 * @since 0.1.0
 */
@Singleton
class SessionManager(context: Context) : ISessionManager {

  override var session = Session()

  private val context: Context

  override var username = ""

  init {
    this.context = context.applicationContext
  }

  override fun putUserOnSession(user: UserDO) {
    session.openSession()
    session.user = user
    username = user.username
  }

  override fun checkLogin(): Boolean {
    if (!isUserLogged()) {
      // User is not logged in -> redirect him to Login Activity
      redirectToLoginActivity()

      return false
    }

    return true
  }

  override fun checkLogin(activity: Activity): Boolean {
    val result = checkLogin()
    if (activity != null && !result) {
      activity.finish()
    }

    return result
  }

  override fun logout() {
    // Prepare logout intent
    val logoutIntent = Intent(ACTION_LOGOUT)
    logoutIntent.putExtra(EXTRA_PARAM_MSG, "Logout User [" + session.user?.username + "]")

    // Initialize Local Broadcast Manager
    val broadcastManager = LocalBroadcastManager.getInstance(context)
    // Send Broadcast about Logout action
    broadcastManager.sendBroadcast(logoutIntent)

    // Clearing all data and close session
    closeSession()

    // TODO Close all db connections

    // After logout redirect user to Loing Activity
    redirectToLoginActivity()
  }

  fun silentLogout() {
    // TODO implement it
    logout()
  }

  private fun isUserLogged(): Boolean {
    return session.isValid()
  }

  private fun redirectToLoginActivity() {
    val intent = Intent(context, SignInActivity::class.java)
    // Closing all the Activities
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    // Add new Flag to start new Activity
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    // Starting Login Fragment Activity
    context.startActivity(intent)
  }

  private fun closeSession() {
    session.closeSession()
    username = ""
  }

}
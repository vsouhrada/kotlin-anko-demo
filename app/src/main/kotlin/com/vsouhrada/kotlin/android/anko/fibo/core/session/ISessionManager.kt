package com.vsouhrada.kotlin.android.anko.fibo.core.session

import android.app.Activity
import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO


/**
 * @author vsouhrada
 * @since 0.1.0
 */
interface ISessionManager {

  /**
   * This method will check user login status If false it will redirect user to login page Else won't do anything.
   * @return true if user is logged else false is returned
   */
  fun checkLogin(): Boolean

  fun checkLogin(activity: Activity): Boolean

  var session: Session

  var username: String

  fun logout()

  /**
   * Create session for currently logged user. This method should be called
   * after user logged.
   * @param[user] currently logged user
   */
  fun putUserOnSession(user: UserDO)

}

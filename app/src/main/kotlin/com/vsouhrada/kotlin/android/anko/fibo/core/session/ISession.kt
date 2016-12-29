package com.vsouhrada.kotlin.android.anko.fibo.core.session

/**
 * @author vsouhrada
 * @since 0.1.0
 */
interface ISession {

  /**
   * Method is call when session should be open and in case of that method *refresh* was called on [ISessionManager]
   * @since 0.1.0
   */
  fun openSession()

  /**
   * Called when session should be close.
   * @since 0.1.0
   */
  fun closeSession()

  /**
   * Method is call when validation of the session is required.
   * @return true in case of that session is valid, otherwise false is returned
   * @since 1.0.0
   */
  fun isValid(): Boolean

  /**
   * Returned Object stored in session under the value *key*.
   * @param key the key value
   * @return data contained under the *key* value
   * @since 0.1.0
   */
  fun getData(key: Int): Any

  /**
   * Store Object in session under the value *key*.
   * @param key the key value
   * @param value the Object which you want to store
   * @since 0.1.0
   */
  fun putData(key: Int, value: Any)

  /**
   * Delete the Object stored in session under the value *key*.
   * @param key the key value
   * @since 0.1.0
   */
  fun removeData(key: Int)


}
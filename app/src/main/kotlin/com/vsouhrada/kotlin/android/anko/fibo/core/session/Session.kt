package com.vsouhrada.kotlin.android.anko.fibo.core.session

import android.util.SparseArray
import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO

/**
 * @author vsouhrada
 * @since 0.1.0
 * @see[ISession]
 */
class Session : ISession {

  var user: UserDO? = null

  private val sessionMemory = SparseArray<Any>()


  override fun openSession() {
    if (sessionMemory.size() > 0) {
      sessionMemory.clear()
    }
  }

  override fun closeSession() {
    user = null
    sessionMemory.clear()
  }

  override fun isValid(): Boolean {
    return user != null
  }

  /**
   * Return data stored on session under the key value.
   * For available key values @see SessionDataKeys

   * @param key the key value
   * *
   * @return the object
   */
  override fun getData(key: Int): Any {
    return sessionMemory.get(key)
  }

  /**
   * Store data on session under the key value.
   * For available key values @see SessionDataKeys

   * @param key the key value
   * *
   * @param value the value object
   */
  override fun putData(key: Int, value: Any) {
    sessionMemory.put(key, value)
  }

  override fun removeData(key: Int) {
    sessionMemory.remove(key)
  }

  override fun toString(): String {
    return "Session(user=$user, sessionMemory=$sessionMemory)"
  }

}
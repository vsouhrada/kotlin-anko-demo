package com.vsouhrada.kotlin.android.anko.fibo.domain.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable


/**
 * @author vsouhrada
 * @version 0.1
 * @since 0.1
 */
@PaperParcel data class UserDO(var id: Int, var username: String, var password: String) : PaperParcelable {

  companion object {
    @JvmField val CREATOR = PaperParcelUserDO.CREATOR
  }

}
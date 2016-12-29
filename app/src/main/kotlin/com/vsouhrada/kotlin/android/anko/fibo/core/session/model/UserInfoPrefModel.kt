package com.vsouhrada.kotlin.android.anko.fibo.core.session.model

import com.chibatching.kotpref.KotprefModel

/**
 * @author vsouhrada
 * @since 0.1.0
 */
object UserInfoPrefModel : KotprefModel() {

  var userId: Int by intPrefVar(default = -1)

}
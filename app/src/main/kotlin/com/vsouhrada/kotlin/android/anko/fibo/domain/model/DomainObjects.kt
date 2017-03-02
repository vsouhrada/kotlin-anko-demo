package com.vsouhrada.kotlin.android.anko.fibo.domain.model

import android.icu.util.CurrencyAmount
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

data class LanguageDO(val id: Int, val name: String)

data class IncomeDO(
        var incomeID: String,
        var creationTimestamp: Long,
        var amount: CurrencyAmount,
        var payerId: Int,
        var paymentId: Int,
        var categoryId: Int,
        var accountId: Int,
        var description: String = "") /*: PaperParcelable {*/
//  companion object {
//    @JvmField val CREATOR: String? = null
//  }
//}

data class CurrencyDO(val name: String, val symbol: String, val language: String)

data class CategoryDO(val categoryId: Int, val parent: Int, val name: String, val language: LanguageDO,
                      var subCategories: List<CategoryDO> = emptyList<CategoryDO>())

data class PaymentDO(val paymentId: Int, val name: String, val language: LanguageDO)
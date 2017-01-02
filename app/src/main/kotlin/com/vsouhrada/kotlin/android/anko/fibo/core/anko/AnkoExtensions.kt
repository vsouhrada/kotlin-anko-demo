package com.vsouhrada.kotlin.android.anko.fibo.core.anko

import android.content.Context
import android.util.TypedValue
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.resources

/**
 * @author vsouhrada
 * @since 0.1.0
 */
inline fun AnkoContext<*>.string(id: Int): String = resources.getString(id)

fun Context.attribute(value : Int) : TypedValue {
  var ret = TypedValue()
  theme.resolveAttribute(value, ret, true)

  return ret
}

fun Context.attrAsDimen(value : Int) : Int{
  return TypedValue.complexToDimensionPixelSize(attribute(value).data, getResources().getDisplayMetrics())
}
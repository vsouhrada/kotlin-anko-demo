package com.vsouhrada.kotlin.android.anko.fibo.core.anko

import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.resources

/**
 * @author vsouhrada
 * @since 0.1.0
 */
inline fun AnkoContext<*>.string(id: Int): String = resources.getString(id)

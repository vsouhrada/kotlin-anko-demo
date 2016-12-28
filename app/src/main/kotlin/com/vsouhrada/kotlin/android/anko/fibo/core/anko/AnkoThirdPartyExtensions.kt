package com.vsouhrada.kotlin.android.anko.fibo.core.anko

import android.view.ViewManager
import com.hkm.ui.processbutton.iml.ActionProcessButton
import com.rengwuxian.materialedittext.MaterialEditText
import org.jetbrains.anko.custom.ankoView

/**
 * @author vsouhrada
 * @since
 */
inline fun ViewManager.materialEditText(theme: Int = 0) = materialEditText(theme) {}
inline fun ViewManager.materialEditText(theme: Int = 0, init: MaterialEditText.() -> Unit) = ankoView(::MaterialEditText, theme, init)

inline fun ViewManager.actionProcessButton(theme: Int = 0) = actionProcessButton(theme) {}
inline fun ViewManager.actionProcessButton(theme: Int = 0, init: ActionProcessButton.() -> Unit) = ankoView(::ActionProcessButton, theme, init)
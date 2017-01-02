package com.vsouhrada.kotlin.android.anko.fibo.core.anko

import android.view.ViewManager
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import com.hkm.ui.processbutton.iml.ActionProcessButton
import com.rengwuxian.materialedittext.MaterialEditText
import org.jetbrains.anko.custom.ankoView


inline fun ViewManager.materialEditText(theme: Int = 0) = materialEditText(theme) {}
inline fun ViewManager.materialEditText(theme: Int = 0, init: MaterialEditText.() -> Unit) = ankoView(::MaterialEditText, theme, init)

inline fun ViewManager.actionProcessButton(theme: Int = 0) = actionProcessButton(theme) {}
inline fun ViewManager.actionProcessButton(theme: Int = 0, init: ActionProcessButton.() -> Unit) = ankoView(::ActionProcessButton, theme, init)

inline fun ViewManager.floatingActionMenu(theme: Int = 0) = floatingActionMenu(theme) {}
inline fun ViewManager.floatingActionMenu(theme: Int = 0, init: FloatingActionMenu.() -> Unit) = ankoView(::FloatingActionMenu, theme, init)

inline fun ViewManager.floatingActionMenuButton(theme: Int = 0) = floatingActionMenuButton(theme) {}
inline fun ViewManager.floatingActionMenuButton(theme: Int = 0, init: FloatingActionButton.() -> Unit) = ankoView(::FloatingActionButton, theme, init)
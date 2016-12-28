/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vsouhrada.kotlin.android.anko.fibo.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Hides the soft keyboard from screen

 * @param view Usually the EditText, but in dynamically  layouts you should pass the layout
 * * instead of the EditText
 * *
 * @return true, if keyboard has been hidden, otherwise false (i.e. the keyboard was not displayed
 * * on the screen or no Softkeyboard because device has hardware keyboard)
 */
fun hideKeyboard(view: View?): Boolean {

  if (view == null) {
    throw NullPointerException("View is null!")
  }

  try {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager ?: return false

    imm.hideSoftInputFromWindow(view.windowToken, 0)
  } catch (e: Exception) {
    return false
  }

  return true
}
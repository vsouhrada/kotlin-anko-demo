package com.vsouhrada.kotlin.android.anko.fibo.function.signin.user.view

import android.widget.EditText
import com.vsouhrada.apps.fibo.function.user.CreateUserEvent
import com.vsouhrada.kotlin.android.anko.fibo.R
import com.vsouhrada.kotlin.android.anko.fibo.core.anko.string
import com.vsouhrada.kotlin.android.anko.fibo.core.rx.RxBus
import com.vsouhrada.kotlin.android.anko.fibo.domain.model.UserDO
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.SignInActivity
import org.jetbrains.anko.*

/**
 * @author vsouhrada
 * @see[AnkoComponent]
 * @see[SignInActivity]
 * @see[ICreateUserView]
 * @since 0.1.0
 */
class CreateUserView(val bus: RxBus) : AnkoComponent<SignInActivity> {

  override fun createView(ui: AnkoContext<SignInActivity>) = with(ui) {
    verticalLayout {
      padding = dip(30)
      val usernameEditText = editText {
        id = R.id.userNameEditText
        hintResource = R.string.create_user_hint_username
        textSize = 24f
      }

      val passwordEditText = editText {
        hintResource = R.string.create_user_hint_password
        textSize = 24f
      }

      button {
        textSize = 25f
        textResource = R.string.create_user_create_button_text
        onClick { view -> handleOnCreateAction(ui, usernameEditText, passwordEditText) }
      }
    }
  }


  private fun handleOnCreateAction(ui: AnkoContext<SignInActivity>, usernameEditText: EditText, passwordEditText: EditText) {
    val username = usernameEditText.text.toString()
    val password = passwordEditText.text.toString()

    if (validateCredentials(username, password)) {
      bus.send(CreateUserEvent(UserDO(username = username, password = password)))
    } else {
      with(ui) {
        alert(title = string(R.string.create_user_alert_failed_title),
                message = string(R.string.create_user_alert_failed_message)) {
          positiveButton(string(R.string.dialog_button_close)) { this@alert.dismiss() }
        }.show()
      }
    }
  }

  private fun validateCredentials(username: String, password: String): Boolean {
    return !username.isNullOrEmpty() && !password.isNullOrEmpty()
  }

}
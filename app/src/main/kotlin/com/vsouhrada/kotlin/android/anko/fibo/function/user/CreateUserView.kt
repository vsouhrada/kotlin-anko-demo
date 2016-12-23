package com.vsouhrada.kotlin.android.anko.fibo.function.user

import android.widget.EditText
import com.vsouhrada.apps.fibo.function.user.CreateUserEvent
import com.vsouhrada.kotlin.android.anko.fibo.R
import com.vsouhrada.kotlin.android.anko.fibo.core.model.UserDO
import com.vsouhrada.kotlin.android.anko.fibo.core.rx.RxBus
import org.jetbrains.anko.*

/**
 * @author vsouhrada
 * @see[AnkoComponent]
 * @see[CreateUserActivity]
 * @version 0.1
 * @since 0.1
 */
class CreateUserView(val bus: RxBus) : AnkoComponent<CreateUserActivity> {

    override fun createView(ui: AnkoContext<CreateUserActivity>) = with(ui) {
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
                textSize = 26f
                textResource = R.string.create_user_create_button_text
                onClick { view -> handleOnCreateAction(usernameEditText, passwordEditText) }
            }
        }
    }

    private fun handleOnCreateAction(usernameEditText: EditText, passwordEditText: EditText) {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (validateCredentials(username, password)) {
            bus.send(CreateUserEvent(UserDO(username = username, password = password)))
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        return true
    }


}
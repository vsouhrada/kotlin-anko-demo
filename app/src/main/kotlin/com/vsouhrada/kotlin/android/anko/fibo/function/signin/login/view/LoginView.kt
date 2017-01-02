package com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.view

import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.hkm.ui.processbutton.iml.ActionProcessButton
import com.rengwuxian.materialedittext.MaterialEditText
import com.rengwuxian.materialedittext.MaterialEditText.FLOATING_LABEL_NORMAL
import com.vsouhrada.kotlin.android.anko.fibo.R
import com.vsouhrada.kotlin.android.anko.fibo.core.anko.materialEditText
import com.vsouhrada.kotlin.android.anko.fibo.core.anko.string
import com.vsouhrada.kotlin.android.anko.fibo.function.signin.login.model.AuthCredentials
import com.vsouhrada.kotlin.android.anko.fibo.utils.hideKeyboard
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputLayout

/**
 * @author vsouhrada
 * @see[AnkoComponent]
 * @see[ILoginView]
 * @since 0.1.0
 */
class LoginView : AnkoComponent<FragmentActivity>, ILoginView {

  lateinit var presenter: ILoginViewPresenter

  lateinit var viewState: LoginViewState

  lateinit var ankoView: AnkoContext<FragmentActivity>

  override var isRestoringViewState = false

  override fun createView(ui: AnkoContext<FragmentActivity>) = with(ui) {
    ankoView = ui
    linearLayout {
      this.gravity = Gravity.CENTER
      lparams(width = matchParent, height = matchParent) {
        orientation = LinearLayout.VERTICAL
        this.gravity = Gravity.CENTER
      }

      scrollView {
        lparams(width = matchParent, height = wrapContent)

        linearLayout {
          this.gravity = Gravity.CENTER
          lparams(width = matchParent, height = matchParent) {
            orientation = LinearLayout.VERTICAL
          }

//      relativeLayout {
//        lparams(width = matchParent, height = wrapContent, weight = 0.5f) {
//          orientation = LinearLayout.VERTICAL
//        }
//
//        textView {
//          this.gravity = Gravity.CENTER
//          text = "Fibo"
//          textSize = 30f
//          lparams(width = matchParent, height = wrapContent) {
//            centerInParent()
//          }
//        }
//      }

          linearLayout {
            id = R.id.loginForm
            lparams(width = dip(300), height = wrapContent) {
              background = ContextCompat.getDrawable(ctx, R.color.white)
              orientation = LinearLayout.VERTICAL
              this.gravity = Gravity.CENTER
              padding = dip(16)
              clipToPadding = false
              bottomMargin = dip(16)
            }

            textInputLayout {
              materialEditText {
                id = R.id.userNameEditText
                lparams(width = matchParent, height = wrapContent) {
                  this.topMargin = dip(12)
                }
                hintResource = R.string.create_user_hint_username
                textSize = 24f
                setIconLeft(ContextCompat.getDrawable(ctx, R.mipmap.ic_username))
                setFloatingLabel(FLOATING_LABEL_NORMAL)
                setPrimaryColor(R.color.colorAccent)
              }
            }

            textInputLayout {
              materialEditText {
                id = R.id.passwordEditText
                lparams(width = matchParent, height = wrapContent)
                inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
                hintResource = R.string.create_user_hint_password
                textSize = 24f
                setIconLeft(ContextCompat.getDrawable(ctx, R.mipmap.ic_password))
              }
            }

            // https://github.com/Kotlin/anko/issues/16
            include<ActionProcessButton>(R.layout.process_button_login) {
              onClick { handleOnLoginAction(ui) }
            }

            checkBox {
              id = R.id.loginStayLoggedBox
              text = string(R.string.login_label_rememberMe)
            }

//        actionProcessButton(theme = R.style.ActionProcessButton) {
//          id = R.id.loginButton
//          lparams(width = matchParent, height = dip(48)) {
//            bottomMargin = dip(16)
//            topMargin = dip(32)
//            leftMargin = dip(16)
//            rightMargin = dip(16)
//            //textColor = ContextCompat.getColor(ctx, R.color.white)
//          }
//          textSize = 18f
//          setAllCaps(true)
//          text = string(R.string.button_login_text)
//          onClick { toast("aaaaa") }
//        }

            textView {
              id = R.id.errorView
              lparams {
                gravity = Gravity.CENTER
                topMargin = dip(8)
                bottomMargin = dip(16)
              }
              textColor = ContextCompat.getColor(ctx, R.color.red_error)
              text = string(R.string.error_view_login_text)
              textSize = 14f
              visibility = View.GONE
            }

            textView {
              lparams(width = matchParent, height = wrapContent) {
                gravity = Gravity.CENTER
                leftMargin = dip(16)
                rightMargin = dip(16)
              }
              textColor = ContextCompat.getColor(ctx, R.color.secondary_text)
              text = "Hint:\nusername = frosty   password = snow"
              textSize = 10f
            }
          }
        }
      }
    }
  }

  private fun handleOnLoginAction(ui: AnkoContext<FragmentActivity>) {
    with(ui.owner) {
      val loginForm = find<LinearLayout>(R.id.loginForm)
      val userNameTextView = find<MaterialEditText>(R.id.userNameEditText)
      val passwordTextView = find<MaterialEditText>(R.id.passwordEditText)
      val rememberMeBox = find<CheckBox>(R.id.loginStayLoggedBox)

      val username = userNameTextView.text.toString()
      val password = passwordTextView.text.toString()

      loginForm.clearAnimation()

      if (username.isNullOrBlank()) {
        with(userNameTextView) {
          clearAnimation()
          startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.shake))
        }

        return
      }

      if (password.isNullOrBlank()) {
        with(passwordTextView) {
          clearAnimation()
          startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.shake))
        }

        return
      }

      // Hide keyboard
      if (!hideKeyboard(userNameTextView)) {
        hideKeyboard(passwordTextView)
      }

      presenter.doLogin(AuthCredentials(userName = username, password = password, rememberMe = rememberMeBox.isChecked))
    }

  }

  override fun showLoginForm() {
    viewState.setShowLoginForm()
    with(ankoView.view) {
      find<TextView>(R.id.errorView).visibility = View.GONE
      setFormEnabled(enabled = true)
      find<ActionProcessButton>(R.id.loginButton).progress = 0
    }
  }

  override fun showError() {
    viewState.setShowError()
    setFormEnabled(true)

    with(ankoView.owner) {
      find<ActionProcessButton>(R.id.loginButton).progress = 0
      if (!isRestoringViewState) {
        // Enable animations only if not restoring view state
        with(find<LinearLayout>(R.id.loginForm)) {
          clearAnimation()
          startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.shake))
        }
      }
      find<TextView>(R.id.errorView).visibility = View.VISIBLE
    }
  }


  override fun showLoading() {
    viewState.setShowLoading()
    with(ankoView.view) {
      find<TextView>(R.id.errorView).visibility = View.GONE
      setFormEnabled(enabled = true)
      find<ActionProcessButton>(R.id.loginButton).progress = 30
    }

  }

  override fun loginSuccessful() {
    with(ankoView.view) {
      find<ActionProcessButton>(R.id.loginButton).progress = 100 // done
      // TODO
//      ankoView.owner.finish()
//      ankoView.owner.overridePendingTransition(0, R.anim.zoom_out)
    }
    ankoView.toast("Login OK")
  }

  private fun setFormEnabled(enabled: Boolean) {
    with(ankoView.view) {
      find<EditText>(R.id.userNameEditText).enabled = enabled
      find<EditText>(R.id.passwordEditText).enabled = enabled
      find<ActionProcessButton>(R.id.loginButton).enabled = enabled
    }
  }
}
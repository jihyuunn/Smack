package com.jihyuunn.smack.Controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.jihyuunn.smack.R
import com.jihyuunn.smack.Sevices.AuthSevice
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginSpinner.visibility = View.INVISIBLE
    }

    fun loginClicked(view: View) {
        enableSpinner(true)
        val email = loginEmailTxt.text.toString()
        val password = loginPwdTxt.text.toString()

        hideKeyboard()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            AuthSevice.loginUser(this, email, password){loginSuccess ->
                if (loginSuccess) {
                    AuthSevice.findUserByEmail(this) { findSuccess ->
                        if (findSuccess) {
                            enableSpinner(false)
                            finish()
                        } else {
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }
            }
        } else {
            Toast.makeText(this, "Please fill in both email and password", Toast.LENGTH_SHORT).show()
        }

    }

    fun signupClicked(view: View) {
        val signupIntent = Intent(this, UserActivity::class.java)
        startActivity(signupIntent)
        finish()
    }

    fun errorToast() {
        Toast.makeText(this, "Something went wrong, Please try again", Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            loginSpinner.visibility = View.VISIBLE
            loginBtn.isEnabled = false
            signupBtn.isEnabled = false

        } else {
            loginSpinner.visibility = View.INVISIBLE
            loginBtn.isEnabled = true
            signupBtn.isEnabled = true
        }
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }

    }
}

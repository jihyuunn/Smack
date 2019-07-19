package com.jihyuunn.smack.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jihyuunn.smack.R
import com.jihyuunn.smack.Sevices.AuthSevice
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginClicked(view: View) {
        val email = loginEmailTxt.text.toString()
        val password = loginPwdTxt.text.toString()

        AuthSevice.loginUser(this, email, password){loginSuccess ->
            if (loginSuccess) {
                AuthSevice.findUserByEmail(this) { findSuccess ->
                    if (findSuccess) {
                        finish()
                    }

                }
            }
        }
    }

    fun signupClicked(view: View) {
        val signupIntent = Intent(this, UserActivity::class.java)
        startActivity(signupIntent)
        finish()
    }
}

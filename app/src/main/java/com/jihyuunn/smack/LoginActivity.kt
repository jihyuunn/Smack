package com.jihyuunn.smack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginClicked(view: View) {

    }

    fun signupClicked(view: View) {
        val signupIntent = Intent(this, UserActivity::class.java)
        startActivity(signupIntent)
    }
}

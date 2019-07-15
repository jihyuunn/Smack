package com.jihyuunn.smack.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jihyuunn.smack.R

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

package com.jihyuunn.smack.Controller

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.jihyuunn.smack.R
import com.jihyuunn.smack.Sevices.AuthSevice
import com.jihyuunn.smack.Sevices.UserDataService
import com.jihyuunn.smack.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_user.*
import java.util.*

class UserActivity : AppCompatActivity() {

    var userAvatar = "profiledefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        createSpinner.visibility = View.INVISIBLE
    }

    fun generateUserAvatar(view: View) {
        val random = Random()
        val color = random.nextInt(2)
        val avatar = random.nextInt(28)

        if (color == 0) {
            userAvatar = "light$avatar"
        } else {
            userAvatar = "dark$avatar"
        }

        val resourceId =  resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImage.setImageResource(resourceId)
    }

    fun generateBackground(view: View) {
        val random = Random()
        val R = random.nextInt(255)
        val G = random.nextInt(255)
        val B = random.nextInt(255)

        createAvatarImage.setBackgroundColor(Color.rgb(R,G,B))
        val savedR = R.toDouble() / 255
        val savedG = G.toDouble() / 255
        val savedB = B.toDouble() / 255

        avatarColor = "[$savedR, $savedG, $savedB, 1]"
    }

    fun createUser(view: View) {
        enableSpinner(true)

        val userName = createUserNameTxt.text.toString()
        val userEmail = createUserEmailTxt.text.toString()
        val userPwd = createPwdTxt.text.toString()

        if (userName.isNotEmpty() && userEmail.isNotEmpty() && userPwd.isNotEmpty()) {

            AuthSevice.registerUser(this, userEmail, userPwd) { registerSuccess ->
                if (registerSuccess) {
                    AuthSevice.loginUser(this, userEmail, userPwd) {loginSuccess ->
                        if (loginSuccess) {
                            AuthSevice.createUser(this, userName, userEmail, userAvatar, avatarColor) {createSuccess ->
                                if (createSuccess) {

                                    val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                                    LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChange)

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
                    errorToast()
                }

            }
        } else {
            Toast.makeText(this, "Make sure your name, email or password", Toast.LENGTH_SHORT).show()
            enableSpinner(false)
        }
    }

    fun errorToast() {
        Toast.makeText(this, "Something went wrong, Please try again", Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            createSpinner.visibility = View.VISIBLE
            createUserBtn.isEnabled = false
            createAvatarImage.isEnabled = false
            generateBackgoundBtn.isEnabled = false

        } else {
            createSpinner.visibility = View.INVISIBLE
            createUserBtn.isEnabled = true
            createAvatarImage.isEnabled = true
            generateBackgoundBtn.isEnabled = true
        }
    }

}

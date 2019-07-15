package com.jihyuunn.smack.Sevices

import android.graphics.Color
import java.util.*

object UserDataService {

    var id = ""
    var avatarColor = ""
    var avatarName = ""
    var email = ""
    var name = ""

    fun returnAvatarColor(components: String) : Int {
        // [0.9490196078431372, 0.3058823529411765, 0.3215686274509804, 1]

        val stripColor = components
            .replace("[", "")
            .replace("]", "")
            .replace(",", "")
        var R = 0
        var G = 0
        var B = 0

        val scanner = Scanner(stripColor)
        if (scanner.hasNext()) {
            R = (scanner.nextDouble() * 255).toInt()
            G = (scanner.nextDouble() * 255).toInt()
            B = (scanner.nextDouble() * 255).toInt()
        }
        return Color.rgb(R,G,B)
    }

    fun logout() {
        id = ""
        avatarColor = ""
        avatarName = ""
        email = ""
        name = ""
        AuthSevice.authToken = ""
        AuthSevice.userEmail = ""
        AuthSevice.loggedIn = false
        AuthSevice.userName = ""
    }
}
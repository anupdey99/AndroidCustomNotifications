package com.anupdey.androidcustomnotifications.util

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import java.util.regex.Matcher
import java.util.regex.Pattern

fun Bundle.print(): String {
    return this.keySet()
        .joinToString(",\n", "{\n", "\n}") { key ->
            " $key=${this[key]}"
        }
}

fun String.getOtp(): String? {
    if (this.startsWith("<#>") && this.endsWith("FyXTPoy9ZDa")) {
        val otp = parseCode(this)
        //Log.d("debug", "OTP $otp")
        return otp
    }
    return null
}

fun parseCode(message: String?): String? {
    message ?: return null
    val p: Pattern = Pattern.compile("\\b\\d{4}\\b")
    val m: Matcher = p.matcher(message)
    var code: String? = ""
    while (m.find()) {
        code = m.group(0)
    }
    return code
}

@Suppress("DEPRECATION")
inline fun <reified T> Context.isServiceRunning() =
    (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
        .getRunningServices(Integer.MAX_VALUE)
        .any { it.service.className == T::class.java.name }
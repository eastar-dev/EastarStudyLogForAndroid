package smart.net

import android.content.Context
import android.log.Log
import android.os.Build.BRAND
import android.os.Build.MODEL
import android.os.Build.VERSION.RELEASE
import android.util.BV
import android.webkit.WebSettings
import com.google.gson.Gson
import dev.eastar.ktx.appName
import dev.eastar.ktx.networkOperatorName
import dev.eastar.ktx.versionName
import smart.base.PP

object UserAgent {
    fun create(context: Context) {
        USER_AGENT = kotlin.runCatching { WebSettings.getDefaultUserAgent(context) }.getOrDefault("")
        Log.w(USER_AGENT)
    }

    const val userAgent: String = "User-Agent"
    lateinit var USER_AGENT: String
}
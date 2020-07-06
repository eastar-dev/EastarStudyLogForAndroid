@file:Suppress("SpellCheckingInspection")

package smart.auth

import android.content.Context
import android.log.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.eastar.operaxinterceptor.event.OperaXEventObservable
import dev.eastar.operaxinterceptor.event.OperaXEvents

object AA {

    fun login(context: Context, json: String) {
        Log.i("login", json)
        FirebaseCrashlytics.getInstance().apply {
            setUserId("custNo")
            setCustomKey("current_level", "userType.name")
            setCustomKey("last_UI_action", "logged_in")
        }
        OperaXEventObservable.notify(OperaXEvents.Logined)
    }

    fun logout() {
        FirebaseCrashlytics.getInstance().setCustomKey("last_UI_action", "logged_out")
        Log.w("logout")
        OperaXEventObservable.notify(OperaXEvents.Logouted)
    }

    fun isLogin(): Boolean = info != null

    var info: Info? = null
    data class Info(val userId: String?)
}

package dev.eastar.studypush.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dev.eastar.studypush.data.model.LoggedInUser
import retrofit2.http.POST
import smart.net.Net
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

class LoginDataSharedPreferenceSource {
    companion object {
        lateinit var pref: SharedPreferences
        private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        fun create(context: Context) {
            pref = EncryptedSharedPreferences
                .create(
                    "defaultSharedPref",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
        }
    }
}
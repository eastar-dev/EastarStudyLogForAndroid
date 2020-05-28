package dev.eastar.studypush.data

import android.content.SharedPreferences
import androidx.core.content.edit
import dev.eastar.studypush.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource, val prefSource: SharedPreferences) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
        val userId = prefSource.getString("userId", null)
        val displayName = prefSource.getString("displayName", null)
        if (userId != null && displayName != null) {
            user = LoggedInUser(userId, displayName)
        }
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(studyGroup: String, username: String, password: String): Result<LoggedInUser> {
        return try {
            val res = dataSource.login(studyGroup, username, password)
            setLoggedInUser(res)
            Result.Success(res)
        } catch (e: Exception) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
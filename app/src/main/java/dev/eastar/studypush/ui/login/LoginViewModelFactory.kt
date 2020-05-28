package dev.eastar.studypush.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.eastar.studypush.data.LoginDataSharedPreferenceSource
import dev.eastar.studypush.data.LoginRepository
import smart.net.Net

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class -> LoginViewModel(LoginRepository(Net.create() , LoginDataSharedPreferenceSource.pref )) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
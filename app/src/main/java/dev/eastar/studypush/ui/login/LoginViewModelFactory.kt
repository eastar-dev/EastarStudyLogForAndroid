package dev.eastar.studypush.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.eastar.studypush.data.LoginDataSource
import dev.eastar.studypush.data.LoginRepository
import smart.net.Net

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = Net.create()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
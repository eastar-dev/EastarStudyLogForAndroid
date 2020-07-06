package dev.eastar.studypush.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import dev.eastar.studypush.R
import dev.eastar.studypush.databinding.AppMainBinding
import dev.eastar.studypush.ui.login.Login
import smart.base.BActivity

@AndroidEntryPoint
class AppMain : BActivity() {
    private lateinit var bb: AppMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = AppMainBinding.inflate(layoutInflater)
        setContentView(bb.root)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        startLogin()
    }


    companion object {
        const val REQ_LOGIN = 2000
    }

    private fun startLogin() {
        startActivityForResult(Intent(mContext, Login::class.java), REQ_LOGIN)
    }

    private fun startLoginCallback(success: Boolean) {
        Toast.makeText(mContext, "$success", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_LOGIN -> startLoginCallback(data?.extras?.getBoolean("result") ?: false)
        }
    }


    fun startLogin2() {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data.toString()
        }.launch(Intent())



        registerForActivityResult(
            object : ActivityResultContract<Unit, FirebaseUser>() {
                override fun createIntent(context: Context, input: Unit?): Intent {
                    val providers = arrayListOf(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.PhoneBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                        AuthUI.IdpConfig.FacebookBuilder().build(),
                        AuthUI.IdpConfig.AnonymousBuilder().build(),
                        AuthUI.IdpConfig.GitHubBuilder().build(),
                        AuthUI.IdpConfig.TwitterBuilder().build()
                    )

                    return AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build()
                }

                override fun parseResult(resultCode: Int, intent: Intent?): FirebaseUser? {
                    val response = IdpResponse.fromResultIntent(intent)
                    return when (resultCode) {
                        Activity.RESULT_OK -> FirebaseAuth.getInstance().currentUser
                        else -> null
                    }
                }
            })
        {
            Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
        }.launch(Unit)

        //if (PP.userId.isBlank())
        //    startActivity(Login::class.java)
    }

    private val launcher: ActivityResultLauncher<Unit> = registerForActivityResult(MonthActivityContract()) {
        Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
    }


}

class MonthActivityContract : ActivityResultContract<Unit, FirebaseUser>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build(),
            AuthUI.IdpConfig.GitHubBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build()
        )

        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }


    override fun parseResult(resultCode: Int, intent: Intent?): FirebaseUser? {
        val response = IdpResponse.fromResultIntent(intent)
        return when (resultCode) {
            Activity.RESULT_OK -> FirebaseAuth.getInstance().currentUser
            else -> null
        }
    }
}
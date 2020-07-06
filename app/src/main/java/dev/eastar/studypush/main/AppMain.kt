package dev.eastar.studypush.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.result.launch
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

        //startLogin()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_login -> startLogin().let { true }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun startLogin() {
        Toast.makeText(mContext, "startLogin", Toast.LENGTH_SHORT).show()
        registerForActivityResult(LoginActivityContract()) {
            Toast.makeText(mContext, "$it", Toast.LENGTH_SHORT).show()
        }.launch()
    }
}

class LoginActivityContract : ActivityResultContract<Unit, FirebaseUser>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            //AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build(),
            AuthUI.IdpConfig.GitHubBuilder().build()
            //AuthUI.IdpConfig.TwitterBuilder().build()
        )

        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    override fun parseResult(resultCode: Int, intent: Intent?): FirebaseUser? {
        return when (resultCode) {
            Activity.RESULT_OK -> FirebaseAuth.getInstance().currentUser
            else -> null
        }
    }
}
package mx.julio.breedog.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mx.julio.breedog.R
import mx.julio.breedog.framework.PreferencesUtils
import mx.julio.breedog.presentation.auth.AuthActivity
import mx.julio.breedog.presentation.main.MainActivity
import javax.inject.Inject

/**
 * Launch Screen.
 * @property preferencesUtils SharedPreferences utils.
 */
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferencesUtils: PreferencesUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val user = preferencesUtils.getLoggedInUser()

            val i = if (user != null) Intent(this, MainActivity::class.java)
            else Intent(this, AuthActivity::class.java)
            startActivity(i)
            finish()

        }, 1000)

    }

}
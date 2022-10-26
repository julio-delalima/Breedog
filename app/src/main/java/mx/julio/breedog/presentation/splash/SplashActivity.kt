package mx.julio.breedog.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import mx.julio.breedog.R
import mx.julio.breedog.framework.Utils
import mx.julio.breedog.presentation.auth.AuthActivity
import mx.julio.breedog.presentation.dogs.list.DogListActivity
import mx.julio.breedog.presentation.main.MainActivity

/**
 * Launch Screen.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val user = Utils.getLoggedInUser(this)

            val i = if (user != null) Intent(this, MainActivity::class.java)
            else Intent(this, AuthActivity::class.java)
            startActivity(i)
            finish()

        }, 1000)

    }

}
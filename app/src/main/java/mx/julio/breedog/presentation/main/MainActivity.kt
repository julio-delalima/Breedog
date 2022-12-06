package mx.julio.breedog.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.julio.breedog.R
import mx.julio.breedog.databinding.ActivityMainBinding
import mx.julio.breedog.presentation.camera.CameraActivity

/**
 * Main activity.
 * @property binding the view.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavView.setupWithNavController(navHostFragment.navController)


        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.camera -> {
                    startActivity(Intent(this@MainActivity, CameraActivity::class.java))
                    false
                }
                else -> true
            }
        }
    }
}
package mx.julio.breedog.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.julio.breedog.R
import mx.julio.breedog.databinding.ActivityMainBinding

/**
 * Main class.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * View.
     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Hook your navigation controller to bottom navigation view
        binding.bottomNavView.setupWithNavController(navHostFragment.navController)
    }
}
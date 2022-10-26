package mx.julio.breedog.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.julio.breedog.R
import mx.julio.breedog.databinding.ActivityAuthBinding
import mx.julio.breedog.framework.Utils
import mx.julio.breedog.framework.data.remote.ApiResponse
import mx.julio.breedog.presentation.auth.login.LoginFragment
import mx.julio.breedog.presentation.auth.login.LoginFragmentDirections
import mx.julio.breedog.presentation.auth.signup.SignupFragment
import mx.julio.breedog.presentation.base.hide
import mx.julio.breedog.presentation.base.show
import mx.julio.breedog.presentation.main.MainActivity

/**
 * Activity for authentication flow.
 * @property binding view.
 * @property viewModel view model.
 */
@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), LoginFragment.LoginActions, SignupFragment.SignupActions {

    private lateinit var binding: ActivityAuthBinding

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupView()
        setupObservers()
    }

    /**
     * View's configuration.
     */
    private fun setupView() {

    }

    /**
     * Configure live observers.
     */
    private fun setupObservers() {
        viewModel.status.observe(this) {
            when (it) {
                is ApiResponse.Error -> {
                    binding.loading.hide()
                    showErrorDialog(it.message)
                }
                is ApiResponse.Success -> binding.loading.hide()
                is ApiResponse.Loading -> binding.loading.show()
            }
        }

        viewModel.user.observe(this) { user ->
            if (user != null) {
                Utils.setLoggedInUser(this@AuthActivity, user)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    /**
     * Show a message.
     * @param message message id.
     */
    private fun showErrorDialog(message: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.text_there_was_an_error)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .create()
            .show()
    }

    override fun onRegister() {
        findNavController(R.id.nav_auth_fragment)
            .navigate(
                LoginFragmentDirections.actionLoginToSignup()
            )
    }

    override fun login(user: String, password: String) {
        viewModel.login(user, password)
    }

    override fun signup(email: String, password: String, confirmation: String) {
        viewModel.signup(email, password, password)
    }
}
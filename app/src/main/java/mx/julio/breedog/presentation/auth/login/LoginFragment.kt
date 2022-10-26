package mx.julio.breedog.presentation.auth.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.julio.breedog.R
import mx.julio.breedog.databinding.FragmentLoginBinding
import mx.julio.breedog.presentation.base.Utils

/**
 * Login fragment.
 * @property binding view.
 * @property actions interface to communicate with parent.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var actions: LoginActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        actions = try {
            context as LoginActions
        } catch (e: java.lang.ClassCastException) {
            throw ClassCastException("$context must implement LoginActions.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    /**
     * View's configuration.
     */
    private fun setupView() {
        binding.login.setOnClickListener {
            validateFields()
        }

        binding.register.setOnClickListener {
            actions.onRegister()
        }
    }

    /**
     * Fields validation.
     */
    private fun validateFields() {
        binding.email.error = null
        binding.password.error = null


        val email = binding.email.text.toString()
        if (Utils.isValidEmail(email).not()) {
            binding.email.error = getString(R.string.error_invalid_email)
            return
        }

        val password = binding.password.text.toString()
        if (password.isEmpty()) {
            binding.password.error = getString(R.string.error_invalid_password)
            return
        }

        actions.login(email, password)

    }

    /**
     * Interface to communicate with parent.
     */
    interface LoginActions {

        /**
         * Method for register action.
         */
        fun onRegister()

        /**
         * Method for login.
         * @param user user.
         * @param password password.
         */
        fun login(user: String, password: String)
    }
}
package mx.julio.breedog.presentation.auth.signup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.julio.breedog.R
import mx.julio.breedog.databinding.FragmentSignupBinding
import mx.julio.breedog.presentation.base.Utils

/**
 * Create account fragment.
 * @property binding view.
 * @property actions interface to communicate with parent.
 */
class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private lateinit var actions: SignupActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        actions = try {
            context as SignupActions
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement SignupActions.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    /**
     * View's configuration.
     */
    private fun setupView() {
        binding.signup.setOnClickListener {
            validateFields()
        }
    }

    /**
     * Fields validation.
     */
    private fun validateFields() {
        binding.email.error = null
        binding.password.error = null
        binding.confirmPassword.error = null


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

        val passwordConfirm = binding.confirmPassword.text.toString()
        if (passwordConfirm.isEmpty()) {
            binding.confirmPassword.error = getString(R.string.error_invalid_password_confirmation)
            return
        }

        if ((password == passwordConfirm).not()) {
            binding.confirmPassword.error = getString(R.string.error_password_confirmation)
            return
        }

        actions.signup(email, password, passwordConfirm)

    }

    /**
     * Interface for calling signup actions.
     */
    interface SignupActions {
        /**
         * Performs signup.
         * @param email user's email.
         * @param password user's password.
         * @param confirmation password confirmation.
         */
        fun signup(email: String, password: String, confirmation: String)
    }
}
package mx.julio.breedog.presentation.main.more

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import mx.julio.breedog.databinding.FragmentMoreBinding
import mx.julio.breedog.framework.PreferencesUtils
import mx.julio.breedog.presentation.auth.AuthActivity
import javax.inject.Inject

/**
 * Fragment to show more information to the user.
 * @property binding view.
 * @property preferencesUtils SharedPreferences utils.
 */
@AndroidEntryPoint
class MoreFragment : Fragment() {

    @Inject
    lateinit var preferencesUtils: PreferencesUtils

    private lateinit var binding: FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    /**
     * View's configuration.
     */
    private fun setupView() {
        binding.logout.setOnClickListener {
            logout()
        }
    }

    /**
     * Removes session.
     */
    private fun logout(){
        preferencesUtils.logout()

        val intent = Intent(requireActivity(), AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}
package mx.julio.breedog.presentation.dogs.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.julio.breedog.databinding.ActivityDogListBinding
import mx.julio.breedog.presentation.dogs.detail.DogDetailActivity
import mx.julio.breedog.presentation.dogs.list.adapter.DogsAdapter

/**
 * Activity to show the whole list of dogs.
 * @property binding the view.
 * @property viewModel the ViewModel.
 * @property adapter the adapter for use in the list.
 */
@AndroidEntryPoint
class DogListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogListBinding

    private val viewModel: DogListViewModel by viewModels()

    private val adapter: DogsAdapter = DogsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
    }

    /**
     * Configure the view.
     */
    private fun setupView() {
        adapter.setOnDogSelectListener {
            val intent = Intent(this, DogDetailActivity::class.java)
            intent.putExtra(DogDetailActivity.ARG_DOG, it)
            startActivity(intent)
        }
        binding.dogs.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.dogs.observe(this) {
            adapter.submitList(it)
        }
    }
}
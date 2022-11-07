package mx.julio.breedog.presentation.dogs.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mx.julio.breedog.databinding.ActivityDogListBinding
import mx.julio.breedog.framework.data.remote.ApiResponse
import mx.julio.breedog.presentation.base.hide
import mx.julio.breedog.presentation.base.show
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
        adapter.setOnDogSelectListener { dog, long ->
            if(!long){
                val intent = Intent(this, DogDetailActivity::class.java)
                intent.putExtra(DogDetailActivity.ARG_DOG, dog)
                startActivity(intent)
            } else viewModel.addDogToUser(dog.id)

        }

        binding.dogs.layoutManager = GridLayoutManager(this@DogListActivity, 3)
        binding.dogs.adapter = adapter

    }

    /**
     * Configure live observers.
     */
    private fun setupObservers() {
        viewModel.dogs.observe(this) {
            adapter.submitList(it)
        }

        viewModel.status.observe(this) {
            when (it) {
                is ApiResponse.Error -> {
                    binding.loading.hide()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG)
                        .show()
                }
                is ApiResponse.Success -> binding.loading.hide()
                is ApiResponse.Loading -> binding.loading.show()
            }
        }
    }
}
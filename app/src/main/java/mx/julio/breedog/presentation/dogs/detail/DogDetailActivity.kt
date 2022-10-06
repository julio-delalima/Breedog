package mx.julio.breedog.presentation.dogs.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import mx.julio.breedog.R
import mx.julio.breedog.databinding.ActivityDogDetailBinding
import mx.julio.breedog.domain.model.Dog

/**
 * Activity to show the dog information.
 * @property binding the view.
 */
class DogDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dog = intent?.extras?.getParcelable<Dog>(ARG_DOG)
        if (dog == null) {
            Toast.makeText(this, getString(R.string.text_dog_not_found), Toast.LENGTH_LONG).show()
            finish()
            return
        }

        binding.dogImage.load(dog.image)
        binding.lifeExpectancy.text = getString(R.string.format_years, dog.lifeExpectancy)
        binding.dog = dog
    }

    companion object {
        /**
         * Constant to pass the dog.
         */
        const val ARG_DOG = "dog"
    }
}
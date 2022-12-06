package mx.julio.breedog.presentation.camera

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import mx.julio.breedog.R
import mx.julio.breedog.databinding.ActivityPreviewBinding

/**
 * Preview screen.
 * @property binding View.
 */
class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.getString(ARG_PHOTO_URI)?.let {
            binding.image.load(Uri.parse(it))
        } ?: run {
            Toast.makeText(this, R.string.error_no_image, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    companion object {
        const val ARG_PHOTO_URI = "photo_uri"
    }
}
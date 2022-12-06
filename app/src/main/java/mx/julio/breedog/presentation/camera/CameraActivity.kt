package mx.julio.breedog.presentation.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import mx.julio.breedog.R
import mx.julio.breedog.databinding.ActivityCameraBinding
import mx.julio.breedog.presentation.camera.PreviewActivity.Companion.ARG_PHOTO_URI
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Dog recognition camera.
 * @property binding View.
 * @property imageCapture Needed to capture images.
 * @property cameraExecutor Executor.
 * @property requestPermissionLauncher request camera permission launcher.
 */
class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    private lateinit var imageCapture: ImageCapture

    private lateinit var cameraExecutor: ExecutorService

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) setupCamera()
            else Toast.makeText(this,
                getString(R.string.error_permission_denied),
                Toast.LENGTH_LONG).show()

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestCameraPermission()
    }


    /**
     * Request camera permission.
     */
    private fun requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    setupCamera()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    AlertDialog.Builder(this)
                        .setTitle(R.string.text_accept_permission)
                        .setMessage(R.string.error_permission_denied)
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                        .setNegativeButton(android.R.string.cancel) { _, _ ->
                        }.show()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        } else {
            setupCamera()
        }
    }

    /**
     * Camera configuration.
     */
    private fun setupCamera() {
        binding.previewView.post {
            cameraExecutor = Executors.newSingleThreadExecutor()

            imageCapture = ImageCapture.Builder()
                .setTargetRotation(binding.previewView.display.rotation)
                .build()

            startCamera()
        }
    }

    /**
     * Start the camera.
     */
    private fun startCamera() {
        binding.capture.setOnClickListener {
            takePhoto()
        }

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(binding.previewView.surfaceProvider)

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // Preview use case
            cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview
            )

            // Capture use case
            cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview, preview)

            // Analysis use case
            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
            imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
                val rotationDegrees = imageProxy.imageInfo.rotationDegrees

                imageProxy.close()
            }
            cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, imageAnalysis, preview)
        }, ContextCompat.getMainExecutor(this))
    }

    /**
     * Take a photo.
     */
    private fun takePhoto() {
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(getOutputPhotoFile()).build()
        imageCapture.takePicture(outputFileOptions, cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(error: ImageCaptureException) {
                    Toast.makeText(this@CameraActivity,
                        getString(R.string.error_taking_photo, error.message),
                        Toast.LENGTH_LONG).show()
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    showPreview(outputFileResults.savedUri.toString())
                }
            })
    }

    /**
     * Show a preview.
     * @param uri File.
     */
    private fun showPreview(uri: String){
        val intent = Intent(this, PreviewActivity::class.java)
        intent.putExtra(ARG_PHOTO_URI, uri)
        startActivity(intent)
    }

    /**
     * Returns the directory.
     * @return Directory.
     */
    private fun getOutputPhotoFile(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name) + ".jpg").apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir
        else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()

        cameraExecutor.shutdown()
    }
}
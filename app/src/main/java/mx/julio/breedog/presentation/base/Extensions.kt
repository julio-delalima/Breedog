package mx.julio.breedog.presentation.base

import android.view.View
import mx.julio.breedog.databinding.LayoutLoaderBinding

/**
 * Show the loader.
 * @receiver the loader view we'll show.
 */
fun LayoutLoaderBinding.show() {
    this.root.visibility = View.VISIBLE
}

/**
 * Hide the loader.
 * @receiver the loader view we'll hide.
 */
fun LayoutLoaderBinding.hide() {
    this.root.visibility = View.GONE
}
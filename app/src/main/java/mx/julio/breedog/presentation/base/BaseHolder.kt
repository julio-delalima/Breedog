package mx.julio.breedog.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstraction of a base holder.
 * @param T the class of the element to be binded.
 * @param view the view for the holder.
 * @constructor Creates a [RecyclerView.ViewHolder].
 */
abstract class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    /**
     * Function used to bind the element.
     * @param item the element to be binded.
     */
    abstract fun bind(item: T)
}
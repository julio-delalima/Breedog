package mx.julio.breedog.presentation.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstraction of a base holder.
 * @param T the class of the element.
 * @param view the view for the holder.
 * @property mContext holder context.
 * @constructor Creates a [RecyclerView.ViewHolder].
 */
abstract class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    var mContext: Context

    init {
        mContext = view.context
    }
    /**
     * Function used to bind the element.
     * @param item the element.
     */
    abstract fun bind(item: T)
}
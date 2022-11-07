package mx.julio.breedog.presentation.dogs.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import mx.julio.breedog.databinding.ItemDogBinding
import mx.julio.breedog.domain.model.Dog

/**
 * Adapter of dogs.
 * @property onDogSelectListener the listener for selection.
 */
class DogsAdapter : ListAdapter<Dog, DogHolder>(DiffCallback) {

    private var onDogSelectListener: ((Dog, Boolean) -> Unit)? = null

    /**
     * Set new value for listener.
     * @param listener the new value.
     */
    fun setOnDogSelectListener(listener: (Dog, Boolean) -> Unit) {
        this.onDogSelectListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        return DogHolder(
            ItemDogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) { position, long ->
            onDogSelectListener?.invoke(getItem(position), long)
        }
    }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
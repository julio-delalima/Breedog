package mx.julio.breedog.presentation.dogs.list.adapter

import androidx.recyclerview.widget.DiffUtil
import mx.julio.breedog.domain.model.Dog

/**
 * Callback for dogs.
 */
object DiffCallback : DiffUtil.ItemCallback<Dog>() {

    override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem == newItem
    }

}
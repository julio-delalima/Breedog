package mx.julio.breedog.presentation.dogs.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import mx.julio.breedog.databinding.ItemDogBinding
import mx.julio.breedog.domain.model.Dog

/**
 * Adapter of dogs.
 */
class DogsAdapter : ListAdapter<Dog, DogHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        return DogHolder(
            ItemDogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
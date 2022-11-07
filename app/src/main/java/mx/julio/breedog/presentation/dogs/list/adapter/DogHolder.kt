package mx.julio.breedog.presentation.dogs.list.adapter

import android.view.View
import coil.load
import mx.julio.breedog.databinding.ItemDogBinding
import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.presentation.base.BaseHolder

/**
 * Represents a dog's view within the list.
 * @property binding binding.
 * @param onSelectListener the listener for selection.
 */
class DogHolder(private val binding: ItemDogBinding, onSelectListener: (Int, Boolean) -> Unit) :
    BaseHolder<Dog>(binding.root) {

    init {
        binding.container.setOnClickListener {
            onSelectListener.invoke(adapterPosition, false)
        }

        binding.container.setOnLongClickListener {
            onSelectListener.invoke(adapterPosition, true)
            true
        }
    }

    override fun bind(item: Dog) {
        if (item.inCollection) {
            binding.image.visibility = View.VISIBLE
            binding.index.visibility = View.GONE

            binding.image.load(item.image)
        } else {
            binding.image.visibility = View.GONE
            binding.index.visibility = View.VISIBLE

            binding.index.text = item.index.toString()
        }
    }
}
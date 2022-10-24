package mx.julio.breedog.presentation.dogs.list.adapter

import coil.load
import mx.julio.breedog.databinding.ItemDogBinding
import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.presentation.base.BaseHolder

/**
 * Represents a dog's view within the list.
 * @property binding binding.
 * @param onSelectListener the listener for selection.
 */
class DogHolder(private val binding: ItemDogBinding, onSelectListener: (Int) -> Unit) : BaseHolder<Dog>(binding.root) {

    init {
        binding.container.setOnClickListener {
            onSelectListener.invoke(adapterPosition)
        }
    }
    override fun bind(item: Dog) {
        binding.image.load(item.image)
    }
}
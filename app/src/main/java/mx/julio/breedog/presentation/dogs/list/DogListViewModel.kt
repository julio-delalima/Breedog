package mx.julio.breedog.presentation.dogs.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.domain.usecase.GetDogs
import javax.inject.Inject

/**
 * ViewModel to manage the Dog list.
 * @property getDogs the use case.
 * @property _dogs internal list of Dogs.
 * @property dogs exposed list of Dogs.
 * @constructor Creates an instance of ViewModel.
 */
@HiltViewModel
class DogListViewModel @Inject constructor(private val getDogs: GetDogs) : ViewModel() {

    private val _dogs = MutableLiveData<List<Dog>>()
    val dogs: LiveData<List<Dog>> get() = _dogs

    init {
        loadDogs()
    }

    /**
     * Retrieves the Dogs list.
     */
    private fun loadDogs() {
        viewModelScope.launch {
            _dogs.value = getDogs.invoke()
        }
    }
}
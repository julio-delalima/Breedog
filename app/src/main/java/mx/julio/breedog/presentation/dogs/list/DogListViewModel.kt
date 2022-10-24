package mx.julio.breedog.presentation.dogs.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.domain.usecase.GetDogs
import mx.julio.breedog.framework.data.remote.ApiResponse
import javax.inject.Inject

/**
 * ViewModel to manage the Dog list.
 * @property getDogs the use case.
 * @property _dogs internal list of Dogs.
 * @property dogs exposed list of Dogs.
 * @property _status internal API status.
 * @property status exposed API status.
 * @constructor Creates an instance of ViewModel.
 */
@HiltViewModel
class DogListViewModel @Inject constructor(private val getDogs: GetDogs) : ViewModel() {

    private val _dogs = MutableLiveData<List<Dog>>()
    val dogs: LiveData<List<Dog>> get() = _dogs

    private val _status = MutableLiveData<ApiResponse<List<Dog>>>()
    val status: LiveData<ApiResponse<List<Dog>>> get() = _status

    init {
        loadDogs()
    }

    /**
     * Retrieves the Dogs list.
     */
    private fun loadDogs() {
        viewModelScope.launch {
            _status.value = ApiResponse.Loading()
            handleResponse(getDogs.invoke())
        }
    }

    /**
     * Method for handling the response.
     * @param response the response we've made.
     */
    private fun handleResponse(response: ApiResponse<List<Dog>>) {
        if (response is ApiResponse.Success) {
            response.data.let { _dogs.value = it }
        }

        _status.value = response
    }
}
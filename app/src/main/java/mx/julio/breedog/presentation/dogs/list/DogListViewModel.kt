package mx.julio.breedog.presentation.dogs.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.domain.usecase.AddDogToUser
import mx.julio.breedog.domain.usecase.GetDogs
import mx.julio.breedog.framework.data.remote.ApiResponse
import javax.inject.Inject

/**
 * ViewModel to manage the Dog list.
 * @property getDogs the use case.
 * @property addDogToUser the user case to add a dog.
 * @property _dogs internal list of Dogs.
 * @property dogs exposed list of Dogs.
 * @property _status internal API status.
 * @property status exposed API status.
 * @constructor Creates an instance of ViewModel.
 */
@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogs: GetDogs,
    private val addDogToUser: AddDogToUser,
) : ViewModel() {

    private val _dogs = MutableLiveData<List<Dog>>()
    val dogs: LiveData<List<Dog>> get() = _dogs

    private val _status = MutableLiveData<ApiResponse<Any>>()
    val status: LiveData<ApiResponse<Any>> get() = _status

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
    @Suppress("UNCHECKED_CAST")
    private fun handleResponse(response: ApiResponse<List<Dog>>) {
        if (response is ApiResponse.Success) {
            response.data.let { _dogs.value = it }
        }

        _status.value = response as ApiResponse<Any>
    }

    /**
     * Method to add a dog to the user collection.
     * @param dogId dog id.
     */
    fun addDogToUser(dogId: Long) {
        viewModelScope.launch {
            _status.value = ApiResponse.Loading( )
            handleAddDogToUserStatus(addDogToUser.invoke(dogId))

        }
    }

    /**
     * Handles the response when a dog was added.
     * @param response the response.
     */
    private fun handleAddDogToUserStatus(response: ApiResponse<Any>){
        if(response is ApiResponse.Success)
            loadDogs()

        _status.value = response
    }
}
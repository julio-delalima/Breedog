package mx.julio.breedog.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.julio.breedog.domain.model.User
import mx.julio.breedog.domain.usecase.Login
import mx.julio.breedog.domain.usecase.Signup
import mx.julio.breedog.framework.data.remote.ApiResponse
import javax.inject.Inject

/**
 * View Model for authentication flow.
 * @property login use case for login.
 * @property signup use case for making signup.
 * @property _user user internal observable instance.
 * @property user exposed user.
 * @property _status internal API status.
 * @property status exposed API status.
 * @constructor Creates a View Model instance.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(private val login: Login, private val signup: Signup) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _status = MutableLiveData<ApiResponse<User>>()
    val status: LiveData<ApiResponse<User>> get() = _status

    /**
     * Function to make the signup.
     * @param email email.
     * @param password password.
     * @param confirmation password confirmation.
     */
    fun signup(email: String, password: String, confirmation: String) {
        viewModelScope.launch {
            _status.value = ApiResponse.Loading()
            handleResponse(signup.invoke(email, password, confirmation))
        }
    }

    /**
     * Function to make the login.
     * @param email user's email.
     * @param password user's password.
     */
    fun login(email: String, password: String){
        viewModelScope.launch {
            _status.value = ApiResponse.Loading()
            handleResponse(login.invoke(email, password))
        }
    }

    /**
     * Method for handling the response.
     * @param response the response we've received.
     */
    private fun handleResponse(response: ApiResponse<User>) {
        if (response is ApiResponse.Success) {
            response.data.let { _user.value = it }
        }

        _status.value = response
    }
}
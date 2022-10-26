package mx.julio.breedog.domain.usecase

import mx.julio.breedog.domain.model.User
import mx.julio.breedog.domain.repository.IAuthRepository
import mx.julio.breedog.framework.data.remote.ApiResponse
import mx.julio.breedog.framework.makeNetworkCall
import javax.inject.Inject

/**
 * Use case to signup.
 * @property repository the repository of auth.
 * @constructor Creates an instance of the use case.
 */
class Signup @Inject constructor(private val repository: IAuthRepository) {

    /**
     * Creates a user account.
     * @return the created user.
     */
    suspend fun invoke(email: String, password: String, passwordConfirmation: String): ApiResponse<User> = makeNetworkCall{
        repository.signup(email, password, passwordConfirmation)
    }
}
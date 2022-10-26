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
class Login @Inject constructor(private val repository: IAuthRepository) {

    /**
     * Login the user.
     * @return the user.
     */
    suspend fun invoke(email: String, password: String): ApiResponse<User> = makeNetworkCall{
        repository.login(email, password)
    }
}
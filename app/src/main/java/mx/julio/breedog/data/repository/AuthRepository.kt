package mx.julio.breedog.data.repository

import mx.julio.breedog.data.source.IAuthSource
import mx.julio.breedog.domain.model.User
import mx.julio.breedog.domain.repository.IAuthRepository
import mx.julio.breedog.framework.data.remote.dto.toDomainUser
import javax.inject.Inject

/**
 * Auth repository implementation.
 * @property source auth remote source.
 * @constructor Creates a repository instance.
 */
class AuthRepository @Inject constructor(private val source: IAuthSource) : IAuthRepository {

    override suspend fun signup(email: String, password: String, passwordConfirmation: String): User {
        val response = source.signup(
            email,
            password,
            passwordConfirmation
        )

        if(!response.is_success)
            throw Exception(response.message)

        return response.data.user.toDomainUser()
    }

    override suspend fun login(email: String, password: String): User {
        val response = source.login(email, password)

        if(!response.is_success)
            throw Exception(response.message)

        return response.data.user.toDomainUser()
    }

}
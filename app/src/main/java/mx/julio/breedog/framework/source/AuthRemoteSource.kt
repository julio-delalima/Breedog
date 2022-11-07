package mx.julio.breedog.framework.source

import mx.julio.breedog.data.source.IAuthSource
import mx.julio.breedog.framework.data.remote.Api
import mx.julio.breedog.framework.data.remote.request.SignInRequestDTO
import mx.julio.breedog.framework.data.remote.request.SignupRequestDTO
import mx.julio.breedog.framework.data.remote.response.DataResponse
import mx.julio.breedog.framework.data.remote.response.UserData
import javax.inject.Inject

/**
 * Auth source implementation.
 * @property api API.
 * @constructor creates an instance.
 */
class AuthRemoteSource @Inject constructor(private val api: Api) : IAuthSource {

    override suspend fun signup(email: String, password: String, passwordConfirmation: String): DataResponse<UserData> {
       return api.signup(
            SignupRequestDTO(
                email,
                password,
                passwordConfirmation
            )
        )
    }

    override suspend fun login(email: String, password: String): DataResponse<UserData> {
        return api.login(
            SignInRequestDTO(
                email,
                password
            )
        )
    }

}
package mx.julio.breedog.data.source

import mx.julio.breedog.framework.data.remote.response.Response
import mx.julio.breedog.framework.data.remote.response.UserData

/**
 * Auth data source abstraction.
 */
interface IAuthSource {

    /**
     * Declaration to signup.
     * @param email user's email.
     * @param password user's password.
     * @param passwordConfirmation password confirmation.
     * @return response.
     */
    suspend fun signup(email: String, password: String, passwordConfirmation: String): Response<UserData>

    /**
     * Declaration to login.
     * @param email user's email.
     * @param password user's password.
     * @return response.
     */
    suspend fun login(email: String, password: String): Response<UserData>
}
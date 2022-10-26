package mx.julio.breedog.domain.repository

import mx.julio.breedog.domain.model.User

/**
 * Auth repository abstraction.
 */
interface IAuthRepository {

    /**
     * Function to signup.
     * @param email user's email.
     * @param password user's password.
     * @param passwordConfirmation password confirmation.
     * @return user.
     */
    suspend fun signup(email: String, password: String, passwordConfirmation: String): User

    /**
     * Function to login.
     * @param email user's email.
     * @param password user's password.
     * @return user.
     */
    suspend fun login(email: String, password: String): User
}
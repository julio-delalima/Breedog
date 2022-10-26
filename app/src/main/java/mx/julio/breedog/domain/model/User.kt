package mx.julio.breedog.domain.model

/**
 * Domain user.
 * @property id user's identifier.
 * @property email user's email.
 * @property authenticationToken password.
 * @constructor Creates an user instance.
 */
data class User(
    val id: Long,
    val email: String,
    val authenticationToken: String,
)
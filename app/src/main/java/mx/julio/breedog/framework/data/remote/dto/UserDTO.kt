package mx.julio.breedog.framework.data.remote.dto

import com.squareup.moshi.Json

/**
 * Model for represent a user DTO.
 * @property id user's identifier.
 * @property email user's email.
 * @property authenticationToken authentication token.
 * @constructor Creates a DTO.
 */
data class UserDTO(
    val id: Long,
    val email: String,
    @field:Json(name = "authentication_token") val authenticationToken: String,
)
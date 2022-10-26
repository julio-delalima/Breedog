package mx.julio.breedog.framework.data.remote.request

import com.squareup.moshi.Json

data class SignupRequestDTO(
    val email: String,
    val password: String,
    @field:Json(name = "password_confirmation") val passwordConfirmation: String,
)
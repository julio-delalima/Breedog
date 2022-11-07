package mx.julio.breedog.framework.data.remote.response

/**
 * Represents a generic response.
 * @property message message for the request.
 * @property is_success if the request was successful.
 */
data class Response(
    val message: String? = null,
    val is_success: Boolean = false,
)
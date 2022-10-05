package mx.julio.breedog.framework.data.remote.response

/**
 * Represents a generic response.
 * @param T the class of data.
 * @property message message for the request.
 * @property is_success if the request was successful.
 * @property data the data.
 */
data class Response<T>(
    val message: String? = null,
    val is_success: Boolean = false,
    val data: T,
)
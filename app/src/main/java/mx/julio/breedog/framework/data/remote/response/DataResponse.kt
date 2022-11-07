package mx.julio.breedog.framework.data.remote.response

/**
 * Represents a generic data
 * response.
 * @param T the class of data.
 * @property data the data.
 */
data class DataResponse<T>(
    val message: String? = null,
    val is_success: Boolean = false,
    val data: T,
)
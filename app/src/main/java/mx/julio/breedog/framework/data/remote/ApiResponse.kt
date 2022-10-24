package mx.julio.breedog.framework.data.remote


/**
 * Generic API response.
 * @param T the API response type.
 */
sealed class ApiResponse<T> {
    /**
     * A success response.
     * @param T the API response type.
     * @property data response data.
     * @constructor Creates a success API response.
     */
    class Success<T>(val data: T) : ApiResponse<T>()

    /**
     * An error response.
     * @param T the API response type.
     * @property message message id.
     * @constructor Creates an error API response.
     */
    class Error<T>(val message: Int) : ApiResponse<T>()

    /**
     * A loading response.
     * @param T the API response type.
     */
    class Loading<T> : ApiResponse<T>()
}
package mx.julio.breedog.framework

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.julio.breedog.R
import mx.julio.breedog.framework.data.remote.ApiResponse
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Build a generic network call.
 * @param call lambda.
 * @return the response.
 */
suspend fun <T> makeNetworkCall(call: suspend () -> T) = withContext(Dispatchers.IO) {
    try {
        ApiResponse.Success(call())
    } catch (e: UnknownHostException) {
        ApiResponse.Error(R.string.error_no_network)
    } catch (e: HttpException) {
        ApiResponse.Error(
            when (e.code()) {
                401 -> R.string.error_unauthorized
                else -> R.string.error_generic
            }
        )
    } catch (e: Exception) {
        ApiResponse.Error(
            when (e.message) {
                "sign_up_error" -> R.string.error_signing_in
                "sign_in_error" -> R.string.error_logging_in
                "user_already_exists" -> R.string.error_existing
                else -> R.string.error_generic
            }
        )
    }
}

package mx.julio.breedog.framework

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.julio.breedog.R
import mx.julio.breedog.framework.data.remote.ApiResponse
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
    } catch (e: Exception) {
        ApiResponse.Error(R.string.error_getting_data)
    }
}

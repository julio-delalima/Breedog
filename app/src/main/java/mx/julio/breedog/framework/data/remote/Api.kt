package mx.julio.breedog.framework.data.remote

import mx.julio.breedog.framework.data.remote.response.DogsData
import mx.julio.breedog.framework.data.remote.response.Response
import retrofit2.http.GET

/**
 * Methods used to make api calls.
 */
interface Api {

    @GET("dogs")
    suspend fun getDogs(): Response<DogsData>
}
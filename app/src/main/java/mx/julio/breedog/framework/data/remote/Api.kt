package mx.julio.breedog.framework.data.remote

import mx.julio.breedog.framework.data.remote.request.SignInRequestDTO
import mx.julio.breedog.framework.data.remote.request.SignupRequestDTO
import mx.julio.breedog.framework.data.remote.response.DogsData
import mx.julio.breedog.framework.data.remote.response.Response
import mx.julio.breedog.framework.data.remote.response.UserData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Methods used to make api calls.
 */
interface Api {

    @GET("dogs")
    suspend fun getDogs(): Response<DogsData>

    @POST("sign_up")
    suspend fun signup(@Body request: SignupRequestDTO) : Response<UserData>

    @POST("sign_in")
    suspend fun login(@Body request: SignInRequestDTO) : Response<UserData>
}
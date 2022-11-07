package mx.julio.breedog.framework.data.remote

import mx.julio.breedog.framework.data.remote.request.AddDogRequestDTO
import mx.julio.breedog.framework.data.remote.request.SignInRequestDTO
import mx.julio.breedog.framework.data.remote.request.SignupRequestDTO
import mx.julio.breedog.framework.data.remote.response.DogsData
import mx.julio.breedog.framework.data.remote.response.DataResponse
import mx.julio.breedog.framework.data.remote.response.Response
import mx.julio.breedog.framework.data.remote.response.UserData
import retrofit2.http.*

/**
 * Methods used to make api calls.
 */
interface Api {

    @GET("dogs")
    suspend fun getDogs(): DataResponse<DogsData>

    @POST("sign_up")
    suspend fun signup(@Body request: SignupRequestDTO): DataResponse<UserData>

    @POST("sign_in")
    suspend fun login(@Body request: SignInRequestDTO): DataResponse<UserData>

    @Headers("${ApiConstants.NEEDS_AUTH_HEADER_KEY}: True")
    @POST("add_dog_to_user")
    suspend fun addDog(@Body request: AddDogRequestDTO): Response

    @Headers("${ApiConstants.NEEDS_AUTH_HEADER_KEY}: True")
    @GET(ApiConstants.GET_USER_DOGS_URL)
    suspend fun getUserDogs(): DataResponse<DogsData>
}
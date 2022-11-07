package mx.julio.breedog.data.source

import mx.julio.breedog.framework.data.remote.request.AddDogRequestDTO
import mx.julio.breedog.framework.data.remote.response.DogsData
import mx.julio.breedog.framework.data.remote.response.DataResponse
import mx.julio.breedog.framework.data.remote.response.Response

/**
 * Dogs data source abstraction.
 */
interface IDogsSource {

    /**
     * Declaration to get the list of dogs.
     * @return response with the list of dogs.
     */
    suspend fun getDogs(): DataResponse<DogsData>

    suspend fun addDogToUser(request: AddDogRequestDTO): Response

    suspend fun getUserDogs(): DataResponse<DogsData>

}
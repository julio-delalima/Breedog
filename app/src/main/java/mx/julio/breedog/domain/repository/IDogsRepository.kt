package mx.julio.breedog.domain.repository

import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.framework.data.remote.ApiResponse
import mx.julio.breedog.framework.data.remote.response.Response

/**
 * Abstraction of dogs repository.
 */
interface IDogsRepository {

    /**
     * Returns a complete list of dogs.
     * @return the list of dogs.
     */
    suspend fun getDogs() : ApiResponse<List<Dog>>

    /**
     * Returns the dog list of specific user.
     * @return the list of dogs.
     */
    suspend fun getUserDogs() : ApiResponse<List<Dog>>

    /**
     * Returns a the user's dog collection.
     * @return the collection.
     */
    suspend fun getDogsCollection() : ApiResponse<List<Dog>>

    /**
     * Add a dog to the user collection
     * @param dogId dog id.
     * @return response.
     */
    suspend fun addDogToUser(dogId: Long): Response


}
package mx.julio.breedog.domain.usecase

import mx.julio.breedog.domain.repository.IDogsRepository
import mx.julio.breedog.framework.data.remote.ApiResponse
import mx.julio.breedog.framework.makeNetworkCall
import javax.inject.Inject

/**
 * Use case to add a dog to the user collection.
 * @property repository dogs repository.
 * @constructor Returns an use case instance.
 */
class AddDogToUser @Inject constructor(private val repository: IDogsRepository) {

    suspend fun invoke(dogId: Long): ApiResponse<Any> = makeNetworkCall {
        val response = repository.addDogToUser(dogId)
        if(!response.is_success)
            throw Exception(response.message)
    }
}
package mx.julio.breedog.domain.usecase

import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.domain.repository.IDogsRepository
import mx.julio.breedog.framework.data.remote.ApiResponse
import mx.julio.breedog.framework.makeNetworkCall
import javax.inject.Inject

/**
 * Use case to get the list of dogs.
 * @property repository the repository of dogs.
 * @constructor Creates an instance of the use case.
 */
class GetDogs @Inject constructor(private val repository: IDogsRepository) {

    /**
     * Gives you the list of dogs.
     * @return the dog list.
     */
    suspend fun invoke(): ApiResponse<List<Dog>> = makeNetworkCall { repository.getDogs() }
}
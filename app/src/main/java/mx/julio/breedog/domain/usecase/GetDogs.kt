package mx.julio.breedog.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.julio.breedog.domain.repository.IDogsRepository
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
    suspend fun invoke() = withContext(Dispatchers.IO) { repository.getDogs() }
}
package mx.julio.breedog.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import mx.julio.breedog.R
import mx.julio.breedog.data.source.IDogsSource
import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.domain.repository.IDogsRepository
import mx.julio.breedog.framework.data.remote.ApiResponse
import mx.julio.breedog.framework.data.remote.dto.toDomainDog
import mx.julio.breedog.framework.data.remote.request.AddDogRequestDTO
import mx.julio.breedog.framework.data.remote.response.Response
import mx.julio.breedog.framework.makeNetworkCall
import javax.inject.Inject

/**
 * Dogs repository implementation.
 * @property source data source.
 * @constructor Create an instance of repository.
 */
class DogsRepository @Inject constructor(private val source: IDogsSource) : IDogsRepository {

    override suspend fun getDogs(): ApiResponse<List<Dog>> = makeNetworkCall {
        val response = source.getDogs()
        response.data.dogs.map { it.toDomainDog() }
    }

    override suspend fun getUserDogs(): ApiResponse<List<Dog>> = makeNetworkCall {
        val response = source.getUserDogs()
        response.data.dogs.map { it.toDomainDog() }
    }

    override suspend fun getDogsCollection(): ApiResponse<List<Dog>> {
        return withContext(Dispatchers.IO) {
            val allDeferred = async { getDogs() }
            val userDeferred = async { getUserDogs() }

            val allResponse = allDeferred.await()
            val userResponse = userDeferred .await()

            if (allResponse is ApiResponse.Error) allResponse
            else if (userResponse is ApiResponse.Error) userResponse
            else if (allResponse is ApiResponse.Success && userResponse is ApiResponse.Success) ApiResponse.Success(
                getCollectionList(allResponse.data, userResponse.data))
            else ApiResponse.Error(R.string.error_generic)

        }
    }

    override suspend fun addDogToUser(dogId: Long): Response {
        return source.addDogToUser(AddDogRequestDTO(dogId))
    }

    /**
     * Private function to make a unique list based on all and user lists.
     * @param all complete list of dogs.
     * @param user specific user dog list.
     * @return unified dog list.
     */
    private fun getCollectionList(all: List<Dog>, user: List<Dog>): List<Dog> {
        return all.map {
            if (user.contains(it)) it
            else Dog.fakeWithIndex(it.index)
        }.sorted()
    }

}
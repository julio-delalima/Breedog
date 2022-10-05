package mx.julio.breedog.data.repository

import mx.julio.breedog.data.source.IDogsSource
import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.domain.repository.IDogsRepository
import mx.julio.breedog.framework.data.remote.model.toDomainDog
import javax.inject.Inject

/**
 * Dogs repository implementation.
 * @property source data source.
 * @constructor Create an instance of repository.
 */
class DogsRepository @Inject constructor(private val source: IDogsSource) : IDogsRepository {

    override suspend fun getDogs(): List<Dog> {
        val response = source.getDogs()
        return response.data.dogs.map { it.toDomainDog() }
    }
}
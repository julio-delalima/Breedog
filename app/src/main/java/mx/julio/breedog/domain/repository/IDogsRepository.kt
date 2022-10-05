package mx.julio.breedog.domain.repository

import mx.julio.breedog.domain.model.Dog

/**
 * Abstraction of dogs repository.
 */
interface IDogsRepository {

    /**
     * Returns a list of existing dogs.
     *
     * @return the list of dogs.
     */
    suspend fun getDogs() : List<Dog>
}
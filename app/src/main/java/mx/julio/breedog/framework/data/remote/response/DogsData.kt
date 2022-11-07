package mx.julio.breedog.framework.data.remote.response

import mx.julio.breedog.framework.data.remote.dto.DogDTO

/**
 * Represents the remote data of dogs.
 * @property dogs the list of dogs.
 */
data class DogsData(
    val dogs: List<DogDTO>,
)

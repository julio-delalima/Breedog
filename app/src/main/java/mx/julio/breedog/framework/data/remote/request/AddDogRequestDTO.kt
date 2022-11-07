package mx.julio.breedog.framework.data.remote.request

import com.squareup.moshi.Json

/**
 * Request model to add a dog to the user's collection.
 * @property dogId dog id.
 * @constructor Generates a request instance.
 */
data class AddDogRequestDTO(
    @field:Json(name = "dog_id") val dogId: Long,
)
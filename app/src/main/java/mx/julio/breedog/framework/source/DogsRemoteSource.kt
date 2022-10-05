package mx.julio.breedog.framework.source

import mx.julio.breedog.data.source.IDogsSource
import mx.julio.breedog.framework.data.remote.Api
import mx.julio.breedog.framework.data.remote.response.DogsData
import mx.julio.breedog.framework.data.remote.response.Response
import javax.inject.Inject

/**
 * Remote source for dogs.
 * @property api the api instance.
 * @constructor Creates a remote source.
 */
class DogsRemoteSource @Inject constructor(private val api: Api) : IDogsSource {
    override suspend fun getDogs(): Response<DogsData> {
        return api.getDogs()
    }
}
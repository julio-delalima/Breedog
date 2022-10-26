package mx.julio.breedog.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.julio.breedog.data.source.IAuthSource
import mx.julio.breedog.data.source.IDogsSource
import mx.julio.breedog.framework.data.remote.Api
import mx.julio.breedog.framework.source.AuthRemoteSource
import mx.julio.breedog.framework.source.DogsRemoteSource
import javax.inject.Singleton

/**
 * Module that provides dependencies related to business implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    /**
     * Provides an instance of the dogs source.
     * @param api client.
     * @return the data source.
     */
    @Provides
    @Singleton
    fun dogsSourceProvider(api: Api): IDogsSource = DogsRemoteSource(api)

    /**
     * Provides an instance of the auth source.
     * @param api client.
     * @return the data source.
     */
    @Provides
    @Singleton
    fun authSourceProvider(api: Api): IAuthSource = AuthRemoteSource(api)
}
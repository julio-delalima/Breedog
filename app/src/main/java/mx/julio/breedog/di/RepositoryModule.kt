package mx.julio.breedog.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.julio.breedog.data.repository.AuthRepository
import mx.julio.breedog.data.repository.DogsRepository
import mx.julio.breedog.data.source.IAuthSource
import mx.julio.breedog.data.source.IDogsSource
import mx.julio.breedog.domain.repository.IAuthRepository
import mx.julio.breedog.domain.repository.IDogsRepository
import javax.inject.Singleton

/**
 * Module that provides dependencies related to repositories.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides an instance of the dogs repository.
     * @param source data source.
     * @return the repository.
     */
    @Provides
    @Singleton
    fun dogsRepositoryProvider(source: IDogsSource): IDogsRepository = DogsRepository(source)

    /**
     * Provides an instance of the auth repository.
     * @param source data source.
     * @return the repository.
     */
    @Provides
    @Singleton
    fun authRepositoryProvider(source: IAuthSource): IAuthRepository = AuthRepository(source)
}
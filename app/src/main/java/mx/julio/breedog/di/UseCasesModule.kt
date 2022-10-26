package mx.julio.breedog.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.julio.breedog.domain.repository.IAuthRepository
import mx.julio.breedog.domain.repository.IDogsRepository
import mx.julio.breedog.domain.usecase.GetDogs
import mx.julio.breedog.domain.usecase.Login
import mx.julio.breedog.domain.usecase.Signup
import javax.inject.Singleton

/**
 * Module that provides use cases.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    /**
     * Provides an instance of the use case for get the dogs.
     * @param repository the dogs repository.
     * @return use case.
     */
    @Provides
    @Singleton
    fun getDogsProvider(repository: IDogsRepository): GetDogs = GetDogs(repository)

    /**
     * Provides an instance of the use case for signup.
     * @param repository the auth repository.
     * @return use case.
     */
    @Provides
    @Singleton
    fun signupProvider(repository: IAuthRepository): Signup = Signup(repository)

    /**
     * Provides an instance of the use case for login.
     * @param repository the auth repository.
     * @return use case.
     */
    @Provides
    @Singleton
    fun loginProvider(repository: IAuthRepository): Login = Login(repository)
}
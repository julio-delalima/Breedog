package mx.julio.breedog.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.julio.breedog.BreedogApp
import mx.julio.breedog.data.source.IAuthSource
import mx.julio.breedog.data.source.IDogsSource
import mx.julio.breedog.framework.PreferencesUtils
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
     * Provides an instance of Application.
     * @param app context.
     * @return application instance.
     */
    @Singleton
    @Provides
    fun applicationProvider(@ApplicationContext app: Context): BreedogApp {
        return app as BreedogApp
    }

    /**
     * Offers an instance of context given the application.
     * @param application application.
     * @return context.
     */
    @Singleton
    @Provides
    fun contextProvider(application: BreedogApp): Context {
        return application.applicationContext
    }

    /**
     * Provides a SharedPreferences instance.
     * @param context app context.
     * @return the instance.
     */
    @Singleton
    @Provides
    fun sharedPreferencesProvider(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "breedog",
            Context.MODE_PRIVATE
        )
    }

    /**
     * Provides an instance of [PreferencesUtils].
     * @param sharedPreferences SharedPreferences instance.
     * @return the instance.
     */
    @Singleton
    @Provides
    fun preferencesUtilsProvider(sharedPreferences: SharedPreferences): PreferencesUtils {
        return PreferencesUtils(sharedPreferences)
    }

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
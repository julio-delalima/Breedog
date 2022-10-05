package mx.julio.breedog.di

import android.content.Context
import mx.julio.breedog.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.julio.breedog.BreedogApp
import mx.julio.breedog.framework.data.remote.Api
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module that provides dependencies related to the API.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val READ_TIMEOUT = 30
    private const val WRITE_TIMEOUT = 30
    private const val CONNECTION_TIMEOUT = 30
    private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L


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
     * Provides a Retrofit instance.
     * @param client http client.
     * @return instance.
     */
    @Provides
    @Singleton
    fun retrofitProvider( client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    /**
     * Generates a client for the requests.
     * @param cache cache to use.
     * @return client.
     */
    @Singleton
    @Provides
    fun okHttpClientProvider(cache: Cache): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(logging)
        }

        return okHttpClientBuilder.build()
    }

    /**
     * Provides and instance of API.
     * @param retrofit client.
     * @return instance.
     */
    @Singleton
    @Provides
    fun apiProvider( retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    /**
     * Exposes the used cache.
     * @param context context.
     * @return cache.
     */
    @Singleton
    @Provides
    internal fun cacheProvider(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }

}
package mx.julio.breedog.di

import android.content.Context
import mx.julio.breedog.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.julio.breedog.framework.PreferencesUtils
import mx.julio.breedog.framework.data.remote.Api
import mx.julio.breedog.framework.data.remote.ApiConstants
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
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

    /**
     * Generates a client for the requests.
     * @param cache cache to use.
     * @return client.
     */
    @Singleton
    @Provides
    fun okHttpClientProvider(cache: Cache, preferences: PreferencesUtils): OkHttpClient {

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

        okHttpClientBuilder.addInterceptor(
            Interceptor{chain ->
                val request: Request = chain.request()
                val token = preferences.getToken()
                val requestBuilder = request.newBuilder()

                if(request.header(ApiConstants.NEEDS_AUTH_HEADER_KEY) != null ){
                    if(token == null) throw java.lang.RuntimeException("Need to be authenticated")
                    else requestBuilder.addHeader("AUTH-TOKEN", token)
                }

                chain.proceed(requestBuilder.build())
            }
        )

        return okHttpClientBuilder.build()
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
     * Provides and instance of API.
     * @param retrofit client.
     * @return instance.
     */
    @Singleton
    @Provides
    fun apiProvider( retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}
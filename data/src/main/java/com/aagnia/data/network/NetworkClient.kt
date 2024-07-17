package com.aagnia.data.network

import com.aagnia.common.utils.FailureResponseWrapper
import com.aagnia.common.utils.ResponseWrapper
import com.aagnia.common.utils.SuccessResponseWrapper
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient(private var config: NetworkServiceConfig) {
    private val _client: Retrofit by lazy {
        initializeRetrofit()
    }

    val client: Retrofit
        get() {
            return _client
        }

    fun <T> makeService(serviceClass: Class<T>): T {
        return _client.create(serviceClass)
    }

    suspend fun <T : Any> execute(api: suspend () -> Response<T>): ResponseWrapper<T> {
        return try {
            val response = api()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                SuccessResponseWrapper(body)
            } else {
                FailureResponseWrapper(Exception())
            }
        } catch (t: Throwable) {
            FailureResponseWrapper(t)
        }
    }

    // Creating the Gson Converter factory
    private fun gsonConverterFactory(): GsonConverterFactory {
        val gson =
            GsonBuilder()
                .setLenient()
                .create()
        return GsonConverterFactory.create(gson)
    }

    // Initialising retrofit
    private fun initializeRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val request = it.request()
                val url = request.url.newBuilder()
                    .build()
                val newRequest = it.request().newBuilder()
                    .url(url)
                    .build()
                it.proceed(newRequest)
            }
            // Setting timeout
            .connectTimeout(100, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(100, TimeUnit.SECONDS) // Read timeout
            .writeTimeout(100, TimeUnit.SECONDS) // Write timeout
            .build()

        val gsonConverter = gsonConverterFactory()

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(config.host)
            .addConverterFactory(gsonConverter)
            .build()
    }
}

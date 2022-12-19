package com.treniti.randomuserapp.di.module.network

import com.google.gson.GsonBuilder
import com.treniti.randomuserapp.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [UserServiceModule::class])
class NetworkModule {

    @Provides
    @Reusable
    fun provideRetrofit(client: OkHttpClient, gson: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(gson)
            .client(client)
            .build()
    }

    @Provides
    @Reusable
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Reusable
    fun provideAPIInterceptor(): Interceptor {
        return Interceptor {
            val originalRequest = it.request()
            val newHttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("key", Constants.API_KEY)
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newHttpUrl)
                .build()
            it.proceed(newRequest)
        }
    }

    @Provides
    @Reusable
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, apiInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Reusable
    fun provideGson(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create()

        return GsonConverterFactory.create(gson)
    }
}
package com.druide.flexmovies.di

import com.druide.flexmovies.BuildConfig
import com.druide.flexmovies.common.Constant.API_KEY
import com.druide.flexmovies.common.Constant.BASE_URL
import com.druide.flexmovies.common.Keys
import com.druide.flexmovies.common.Utils.json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
                .addInterceptor {
                    val oldReq = it.request()
                    val newUrl = oldReq.url.newBuilder()
                        .addQueryParameter(API_KEY, Keys.apiKey())
                        .build()
                    val newReq = oldReq.newBuilder().url(newUrl).build()
                    it.proceed(newReq)
                }
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.HEADERS
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
        }.build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }
}
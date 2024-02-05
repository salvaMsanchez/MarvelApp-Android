package com.example.marvelapp.di

import com.example.marvelapp.data.remote.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val TIMESTAMP: String = "1"
const val API_KEY: String = ""
const val HASH: String = ""

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("ts", TIMESTAMP)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("hash", HASH)
                .build()
            val request = chain.request().newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }.build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun providesArtInstituteChicagoApi(retrofit: Retrofit): MarvelApi =
        retrofit.create(MarvelApi::class.java)
}
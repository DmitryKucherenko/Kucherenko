package ru.tinkoff.lab.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.tinkoff.lab.data.network.ApiService
import javax.inject.Singleton


@Module
class DataModule {
    private val BASE_URL = "https://kinopoiskapiunofficial.tech"

    @Singleton
    @Provides
    fun provideRetrofit(okhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okhttp)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()
}
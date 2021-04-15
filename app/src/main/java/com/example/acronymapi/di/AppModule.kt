package com.example.acronymapi.di

import com.example.acronymapi.data.remote.AcronymService
import com.example.acronymapi.repositories.AcronymRepository
import com.example.acronymapi.repositories.DefaultAcronymRepository
import com.example.acronymapi.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideClient() : OkHttpClient = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            OkHttpClient.Builder().addInterceptor(it).build()
        }

    @Singleton
    @Provides
    fun provideAcronymApi(client: OkHttpClient) : AcronymService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(AcronymService::class.java)

    @Singleton
    @Provides
    fun provideDefaultAcronymRepository(
        acronymService: AcronymService
    ) = DefaultAcronymRepository(acronymService) as AcronymRepository

}
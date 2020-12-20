package com.kairosapp.albumsleboncoin.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kairosapp.albumsleboncoin.repository.AlbumRepository
import com.kairosapp.albumsleboncoin.repository.AlbumRepositoryImpl
import com.kairosapp.albumsleboncoin.service.LeboncoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideLeboncoinRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideLeboncoinService(leboncoinRetrofit: Retrofit): LeboncoinService =
        leboncoinRetrofit.create(LeboncoinService::class.java)

    @Provides
    @Singleton
    fun provideAlbumRepository(leboncoinService: LeboncoinService): AlbumRepository =
        AlbumRepositoryImpl(leboncoinService)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .create()
}
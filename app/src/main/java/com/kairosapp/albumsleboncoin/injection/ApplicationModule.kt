package com.kairosapp.albumsleboncoin.injection

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kairosapp.albumsleboncoin.repository.AlbumRepository
import com.kairosapp.albumsleboncoin.repository.AlbumRepositoryImpl
import com.kairosapp.albumsleboncoin.service.LeboncoinService
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

private const val PICASSO_DISK_CACHE_SIZE_LIMIT = 25L * 1024L * 1024L // 25mo

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

    @Provides
    @Singleton
    fun providePicasso(@ApplicationContext context: Context): Picasso = Picasso.Builder(context)
        .downloader(
            OkHttp3Downloader(
                OkHttpClient.Builder()
                    .cache(Cache(File(context.cacheDir, "images"), PICASSO_DISK_CACHE_SIZE_LIMIT))
                    .build()
            )
        )
        .build()
}
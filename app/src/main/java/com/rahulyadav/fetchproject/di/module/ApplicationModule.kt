package com.rahulyadav.fetchproject.di.module

import android.content.Context
import com.rahulyadav.fetchproject.FetchApplication
import com.rahulyadav.fetchproject.data.api.NetworkService
import com.rahulyadav.fetchproject.di.ApplicationContext
import com.rahulyadav.fetchproject.di.BaseUrl
import com.rahulyadav.fetchproject.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: FetchApplication) {

    @ApplicationContext
    @Provides
    fun provideContext() :Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BASE_URL


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(@BaseUrl baseUrl: String, gsonConverterFactory: GsonConverterFactory):NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

}
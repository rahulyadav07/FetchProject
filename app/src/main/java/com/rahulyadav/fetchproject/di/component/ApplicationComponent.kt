package com.rahulyadav.fetchproject.di.component

import android.content.Context
import com.rahulyadav.fetchproject.FetchApplication
import com.rahulyadav.fetchproject.data.api.NetworkService
import com.rahulyadav.fetchproject.data.repository.FetchRepository
import com.rahulyadav.fetchproject.di.ApplicationContext
import com.rahulyadav.fetchproject.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: FetchApplication)

    @ApplicationContext
    fun getContext():Context

    fun getNetworkService() :NetworkService

    fun getFetchRepository():FetchRepository

}
package com.rahulyadav.fetchproject

import android.app.Application
import com.rahulyadav.fetchproject.di.component.ApplicationComponent
import com.rahulyadav.fetchproject.di.component.DaggerApplicationComponent
import com.rahulyadav.fetchproject.di.module.ApplicationModule

class FetchApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}
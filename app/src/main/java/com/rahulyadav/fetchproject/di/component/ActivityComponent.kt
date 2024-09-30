package com.rahulyadav.fetchproject.di.component

import com.rahulyadav.fetchproject.di.ActivityScope
import com.rahulyadav.fetchproject.di.module.ActivityModule
import com.rahulyadav.fetchproject.ui.activity.FetchActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun injectFetchActivity(activity: FetchActivity)

}
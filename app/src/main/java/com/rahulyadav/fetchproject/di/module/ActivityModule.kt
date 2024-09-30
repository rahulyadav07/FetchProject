package com.rahulyadav.fetchproject.di.module


import android.content.Context
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rahulyadav.fetchproject.data.repository.FetchRepository
import com.rahulyadav.fetchproject.di.ActivityContext
import com.rahulyadav.fetchproject.ui.activity.fetchactivity.FetchViewModel
import com.rahulyadav.fetchproject.base.ViewModelProviderFactory
import com.rahulyadav.fetchproject.ui.activity.fetchactivity.FetchAdapter
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext():Context {
        return activity
    }

    @Provides
    fun provideHiringAdapter() = FetchAdapter(ArrayList())

    @com.rahulyadav.fetchproject.di.ArrayList
    @Provides
    fun provideMutableListIdOptions(): MutableList<String> {
        return mutableListOf()
    }

    @Provides
    fun provideArrayAdapter(@ActivityContext context: Context,   @com.rahulyadav.fetchproject.di.ArrayList listIdOptions: List<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, listIdOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }
    @Provides
    fun provideFetchViewModel(fetchRepository: FetchRepository): FetchViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(FetchViewModel::class){
            FetchViewModel(fetchRepository)
        })[FetchViewModel::class.java]
    }
}
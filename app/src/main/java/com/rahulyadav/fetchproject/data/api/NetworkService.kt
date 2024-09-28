package com.rahulyadav.fetchproject.data.api

import com.rahulyadav.fetchproject.data.model.HiringData
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("hiring.json")
    suspend fun getData():List<HiringData>
}
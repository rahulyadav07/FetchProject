package com.rahulyadav.fetchproject.data.repository

import com.rahulyadav.fetchproject.data.api.NetworkService
import com.rahulyadav.fetchproject.data.model.HiringData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchRepository @Inject constructor(private val networkService: NetworkService) {
    fun getHiringData(): Flow<List<HiringData>> = flow {
        val hiringData = networkService.getData().filter {  !it.hiringName.isNullOrEmpty()} //  if hiringName is null or empty don't consider in list , did because as per the docs requirement.
        emit(hiringData)
    }.flowOn(Dispatchers.IO)
}
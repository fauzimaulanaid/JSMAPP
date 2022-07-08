package com.fauzimaulana.jsmapp.core.data.source

import com.fauzimaulana.jsmapp.core.data.source.remote.ApiResponse
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(loadData(apiResponse.data)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("No data found"))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun loadData(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result
}
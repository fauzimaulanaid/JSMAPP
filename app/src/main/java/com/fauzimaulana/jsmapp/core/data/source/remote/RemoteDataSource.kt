package com.fauzimaulana.jsmapp.core.data.source.remote

import android.util.Log
import com.fauzimaulana.jsmapp.core.data.source.remote.network.ApiService
import com.fauzimaulana.jsmapp.core.data.source.remote.response.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllUser(): Flow<ApiResponse<List<UserItem>>> {
        return flow {
            try {
                val response = apiService.getAllUser()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}

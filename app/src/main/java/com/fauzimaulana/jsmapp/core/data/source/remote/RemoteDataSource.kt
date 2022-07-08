package com.fauzimaulana.jsmapp.core.data.source.remote

import android.util.Log
import com.fauzimaulana.jsmapp.core.data.source.remote.network.ApiService
import com.fauzimaulana.jsmapp.core.data.source.remote.response.CreateUserResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.LoginResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.RegisterResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    fun registerUser(email: String, password: String): Flow<ApiResponse<RegisterResponse>> {
        return flow {
            try {
                val response = apiService.registerUser(email, password)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun loginUser(email: String, password: String): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                val response = apiService.loginUser(email, password)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

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

    fun createUser(name: String, job: String): Flow<ApiResponse<CreateUserResponse>> {
        return flow {
            try {
                val response = apiService.createUser(name, job)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

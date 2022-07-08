package com.fauzimaulana.jsmapp.core.data.source

import com.fauzimaulana.jsmapp.core.data.source.remote.ApiResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.jsmapp.core.data.source.remote.response.UserItem
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.domain.repository.IJSMRepository
import com.fauzimaulana.jsmapp.core.utils.DataMapper
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class JSMRepository(private val remoteDataSource: RemoteDataSource): IJSMRepository {
    override fun getAllUser(): Flow<Resource<List<UserModel>>> {
        return object : NetworkBoundResource<List<UserModel>, List<UserItem>>() {
            override fun createCall(): Flow<ApiResponse<List<UserItem>>> =
                remoteDataSource.getAllUser()

            override fun loadData(data: List<UserItem>): List<UserModel> =
                DataMapper.mapUserResponseToDomain(data)
        }.asFlow()
    }
}
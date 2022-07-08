package com.fauzimaulana.jsmapp.core.data.source

import com.fauzimaulana.jsmapp.core.data.source.remote.ApiResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.jsmapp.core.data.source.remote.response.CreateUserResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.LoginResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.RegisterResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.UserItem
import com.fauzimaulana.jsmapp.core.domain.model.CreateUserModel
import com.fauzimaulana.jsmapp.core.domain.model.LoginModel
import com.fauzimaulana.jsmapp.core.domain.model.RegisterModel
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.domain.repository.IJSMRepository
import com.fauzimaulana.jsmapp.core.uttils.DataMapper
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class JSMRepository(private val remoteDataSource: RemoteDataSource): IJSMRepository {
    override fun registerUser(email: String, password: String): Flow<Resource<RegisterModel>> {
        return object : NetworkBoundResource<RegisterModel, RegisterResponse>() {
            override fun createCall(): Flow<ApiResponse<RegisterResponse>> =
                remoteDataSource.registerUser(email, password)

            override fun loadData(data: RegisterResponse): RegisterModel =
                DataMapper.mapRegisterResponseToDomain(data)
        }.asFlow()
    }

    override fun loginUser(email: String, password: String): Flow<Resource<LoginModel>> {
        return object : NetworkBoundResource<LoginModel, LoginResponse>() {
            override fun createCall(): Flow<ApiResponse<LoginResponse>> =
                remoteDataSource.loginUser(email, password)

            override fun loadData(data: LoginResponse): LoginModel =
                DataMapper.mapLoginResponseToDomain(data)
        }.asFlow()
    }

    override fun getAllUser(): Flow<Resource<List<UserModel>>> {
        return object : NetworkBoundResource<List<UserModel>, List<UserItem>>() {
            override fun createCall(): Flow<ApiResponse<List<UserItem>>> =
                remoteDataSource.getAllUser()

            override fun loadData(data: List<UserItem>): List<UserModel> =
                DataMapper.mapUserResponseToDomain(data)
        }.asFlow()
    }

    override fun createUser(name: String, job: String): Flow<Resource<CreateUserModel>> {
        return object : NetworkBoundResource<CreateUserModel, CreateUserResponse>() {
            override fun createCall(): Flow<ApiResponse<CreateUserResponse>> =
                remoteDataSource.createUser(name, job)

            override fun loadData(data: CreateUserResponse): CreateUserModel =
                DataMapper.mapCreateUserResponseToDomain(data)
        }.asFlow()
    }
}
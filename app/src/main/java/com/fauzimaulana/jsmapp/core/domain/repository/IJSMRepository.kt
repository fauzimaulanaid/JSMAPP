package com.fauzimaulana.jsmapp.core.domain.repository

import com.fauzimaulana.jsmapp.core.domain.model.CreateUserModel
import com.fauzimaulana.jsmapp.core.domain.model.LoginModel
import com.fauzimaulana.jsmapp.core.domain.model.RegisterModel
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IJSMRepository {
    fun registerUser(email: String,password: String): Flow<Resource<RegisterModel>>

    fun loginUser(email: String, password: String): Flow<Resource<LoginModel>>

    fun getAllUser(): Flow<Resource<List<UserModel>>>

    fun createUser(name: String, job: String): Flow<Resource<CreateUserModel>>
}
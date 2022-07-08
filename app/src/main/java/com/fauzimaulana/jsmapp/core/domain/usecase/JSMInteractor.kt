package com.fauzimaulana.jsmapp.core.domain.usecase

import com.fauzimaulana.jsmapp.core.domain.model.CreateUserModel
import com.fauzimaulana.jsmapp.core.domain.model.LoginModel
import com.fauzimaulana.jsmapp.core.domain.model.RegisterModel
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.domain.repository.IJSMRepository
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class JSMInteractor(private val JSMRepository: IJSMRepository): JSMUseCase {
    override fun registerUser(email: String, password: String): Flow<Resource<RegisterModel>> =
        JSMRepository.registerUser(email, password)

    override fun loginUser(email: String, password: String): Flow<Resource<LoginModel>> =
        JSMRepository.loginUser(email, password)

    override fun getAllUser(): Flow<Resource<List<UserModel>>> =
        JSMRepository.getAllUser()

    override fun createUser(name: String, job: String): Flow<Resource<CreateUserModel>> =
        JSMRepository.createUser(name, job)
}
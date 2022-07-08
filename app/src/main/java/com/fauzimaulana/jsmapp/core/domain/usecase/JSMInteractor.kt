package com.fauzimaulana.jsmapp.core.domain.usecase

import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.domain.repository.IJSMRepository
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class JSMInteractor(private val JSMRepository: IJSMRepository): JSMUseCase {
    override fun getAllUser(): Flow<Resource<List<UserModel>>> =
        JSMRepository.getAllUser()
}
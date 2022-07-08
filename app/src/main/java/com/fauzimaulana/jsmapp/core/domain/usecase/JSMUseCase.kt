package com.fauzimaulana.jsmapp.core.domain.usecase

import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface JSMUseCase {
    fun getAllUser(): Flow<Resource<List<UserModel>>>
}
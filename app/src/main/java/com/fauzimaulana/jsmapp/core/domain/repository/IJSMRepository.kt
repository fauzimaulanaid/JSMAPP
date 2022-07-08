package com.fauzimaulana.jsmapp.core.domain.repository

import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IJSMRepository {
    fun getAllUser(): Flow<Resource<List<UserModel>>>
}
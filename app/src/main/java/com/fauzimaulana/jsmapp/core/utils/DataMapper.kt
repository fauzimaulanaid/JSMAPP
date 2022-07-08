package com.fauzimaulana.jsmapp.core.utils

import com.fauzimaulana.jsmapp.core.data.source.remote.response.CreateUserResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.LoginResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.RegisterResponse
import com.fauzimaulana.jsmapp.core.data.source.remote.response.UserItem
import com.fauzimaulana.jsmapp.core.domain.model.CreateUserModel
import com.fauzimaulana.jsmapp.core.domain.model.LoginModel
import com.fauzimaulana.jsmapp.core.domain.model.RegisterModel
import com.fauzimaulana.jsmapp.core.domain.model.UserModel

object DataMapper {
    fun mapRegisterResponseToDomain(input: RegisterResponse) = RegisterModel(
        id = input.id,
        token = input.token
    )

    fun mapLoginResponseToDomain(input: LoginResponse) = LoginModel(
        token = input.token
    )

    fun mapCreateUserResponseToDomain(input: CreateUserResponse) = CreateUserModel(
        createdAt = input.createdAt,
        name = input.name,
        id = input.id,
        job = input.job
    )

    fun mapUserResponseToDomain(input: List<UserItem>): List<UserModel> =
        input.map {
            UserModel(
                lastName = it.lastName,
                id = it.id,
                avatar = it.avatar,
                firstName = it.firstName,
                email = it.email
            )
        }
}
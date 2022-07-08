package com.fauzimaulana.jsmapp.core.utils

import com.fauzimaulana.jsmapp.core.data.source.remote.response.UserItem
import com.fauzimaulana.jsmapp.core.domain.model.UserModel

object DataMapper {
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
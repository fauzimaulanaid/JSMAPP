package com.fauzimaulana.jsmapp.core.data.source.remote.network

import com.fauzimaulana.jsmapp.core.data.source.remote.response.*
import retrofit2.http.*

interface ApiService {
    @GET("api/users")
    suspend fun getAllUser(): ListUserResponse
}
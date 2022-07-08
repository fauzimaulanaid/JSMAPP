package com.fauzimaulana.jsmapp.core.data.source.remote.network

import com.fauzimaulana.jsmapp.core.data.source.remote.response.*
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("api/register")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("api/users")
    suspend fun getAllUser(): ListUserResponse

    @FormUrlEncoded
    @POST("api/users")
    suspend fun createUser(
        @Field("name") name: String,
        @Field("job") job: String,
    ): CreateUserResponse

    @FormUrlEncoded
    @PUT("api/users/{user_id}")
    suspend fun updateUser(
        id: String,
        @Field("name") name: String,
        @Field("job") job: String,
    ): UpdateUserResponse

    @DELETE("api/users/{user_id}")
    suspend fun deleteUser(
        id: String
    )

}
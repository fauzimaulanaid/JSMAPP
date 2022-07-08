package com.fauzimaulana.jsmapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("job")
	val job: String
)

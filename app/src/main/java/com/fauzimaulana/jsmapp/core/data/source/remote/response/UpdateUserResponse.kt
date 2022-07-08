package com.fauzimaulana.jsmapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateUserResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("job")
	val job: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

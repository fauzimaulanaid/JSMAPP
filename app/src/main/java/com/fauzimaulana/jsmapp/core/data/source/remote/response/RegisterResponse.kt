package com.fauzimaulana.jsmapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("token")
	val token: String
)

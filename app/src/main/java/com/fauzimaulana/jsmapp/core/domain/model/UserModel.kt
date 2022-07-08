package com.fauzimaulana.jsmapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val lastName: String,
    val id: Int,
    val avatar: String,
    val firstName: String,
    val email: String
): Parcelable

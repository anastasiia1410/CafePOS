package com.example.data.network.entity.request

import com.google.gson.annotations.SerializedName

internal data class SignUpRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val login: String,
    @SerializedName("email")
    val email: String
)

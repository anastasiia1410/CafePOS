package com.example.data.network.entity.response

import com.google.gson.annotations.SerializedName

internal data class SignUpResponse(@SerializedName("sessionToken") val token : String)

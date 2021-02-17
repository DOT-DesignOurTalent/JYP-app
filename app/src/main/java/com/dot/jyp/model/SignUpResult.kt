package com.dot.jyp.model

import com.google.gson.annotations.SerializedName

data class SignUpResult(
    @SerializedName("email") val email : String,
    @SerializedName("password") val password : String,
    @SerializedName("nickname") val nickname : String
)
package com.dot.jyp.model

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("email") val email : String,
    @SerializedName("passphrase") val passphrase : String
)

data class UserAccount(
    @SerializedName("email") val email : String,
    @SerializedName("passphrase") val passphrase : String
)
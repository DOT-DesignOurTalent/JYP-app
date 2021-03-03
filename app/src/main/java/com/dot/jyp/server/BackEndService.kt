package com.dot.jyp.server

import com.dot.jyp.model.Account
import retrofit2.Call
import retrofit2.http.*

interface BackEndService {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/user/signup")
    fun signUp(
        @Body account : Account
    ): Call<Void>

    @POST("/api/v1/user/login")
    fun logIn(
        @Field("email") email: String,
        @Field("passphrase") passphrase: String
    )
}
package com.dot.jyp.server

import com.dot.jyp.model.SignUpResult
import retrofit2.Call
import retrofit2.http.*

interface BackEndService {
    @FormUrlEncoded
    @POST("/api/v1/user/signup")
    fun signUp(
        @Field("email") email : String,
        @Field("passphrase") passphrase : String
    ): Call<SignUpResult>

    @POST("/api/v1/user/login")
    fun logIn(
        @Field("email") email: String,
        @Field("passphrase") passphrase: String
    )

}
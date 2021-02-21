package com.dot.jyp.server

import com.dot.jyp.model.SignUpResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BackEndService {
    @GET("/api/v1/user/sign-up")
    fun signUp(
        @Query("email") email : String,
        @Query("password") password : String
    ): Call<SignUpResult>
}
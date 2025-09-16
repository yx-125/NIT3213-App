package com.example.nit3213app2.data.auth

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {
    @POST("{campus}/auth")
    suspend fun login(
        @Path("campus") campus: String,
        @Body request: AuthRequest
    ): AuthResponse
}
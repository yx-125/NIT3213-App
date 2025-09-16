package com.example.nit3213app2.data.auth

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Retrofit API definition for authentication-related endpoint
 */
interface AuthApiService {
    /**
     * Sends a login request to the API
     */
    @POST("{campus}/auth")
    suspend fun login(
        @Path("campus") campus: String,
        @Body request: AuthRequest
    ): AuthResponse
}
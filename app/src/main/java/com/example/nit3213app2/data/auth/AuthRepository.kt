package com.example.nit3213app2.data.auth

import javax.inject.Inject

/**
 * Repository responsible for handling authentication-related operations
 */
class AuthRepository @Inject constructor(
    private val api: AuthApiService
) {
    /**
     * Attempts to log a user in with the provided credentials
     */
    suspend fun login(campus: String, username: String, password: String): AuthResponse {
        return api.login(campus, AuthRequest(username, password))
    }
}
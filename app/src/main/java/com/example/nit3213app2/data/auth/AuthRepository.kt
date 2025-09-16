package com.example.nit3213app2.data.auth

import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApiService
) {
    suspend fun login(campus: String, username: String, password: String): AuthResponse {
        return api.login(campus, AuthRequest(username, password))
    }
}
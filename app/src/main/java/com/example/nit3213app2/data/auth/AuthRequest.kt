package com.example.nit3213app2.data.auth

data class AuthRequest(
    val username: String, // student's first name
    val password: String // student ID (1234567, no "s")
)
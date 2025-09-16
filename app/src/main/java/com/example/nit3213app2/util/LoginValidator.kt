package com.example.nit3213app2.util

/**
 * Represents validation results for login input fields
 */
data class ValidationErrors(
    val campusError: String? = null,
    val usernameError: String? = null,
    val passwordError: String? = null,
) {
    val isValid get() = campusError == null && usernameError == null && passwordError == null
}

/**
 * Utility object for validating login input
 */
object LoginValidator {
    // List of valid campus identifiers
    private val validCampuses = listOf("footscray", "sydney", "br")

    /**
     * Validates the provided login input values
     */
    fun validateLoginInput(campus: String, username: String, password: String): ValidationErrors {
        var campusError: String? = null
        var usernameError: String? = null
        var passwordError: String? = null

        if (campus.isBlank()) {
            campusError = "Campus is required"
        } else if (!validCampuses.contains(campus.lowercase())) {
            campusError = "Invalid campus"
        }

        if (username.isBlank()) {
            usernameError = "Username is required"
        } else if (!username.matches(Regex("^[A-Za-z]+$"))) {
            usernameError = "Username must contain only letters"
        }

        if (password.isBlank()) {
            passwordError = "Password is required"
        } else if (!password.matches(Regex("^\\d{7}$"))) {
            passwordError = "Password must be exactly 7 digits"
        }

        return ValidationErrors(campusError, usernameError, passwordError)
    }
}
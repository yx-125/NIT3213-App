package com.example.nit3213app2

import com.example.nit3213app2.util.LoginValidator
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginValidatorTest {

    private lateinit var validator: LoginValidator

    @Before
    fun setup() {
        validator = LoginValidator
    }

    @Test
    fun `campus is empty`() {
        val result = validator.validateLoginInput("", "Sandra", "8079691")
        assertFalse(result.isValid)
        assertEquals("Campus is required", result.campusError)
        assertNull(result.usernameError)
        assertNull(result.passwordError)
    }

    @Test
    fun `username is empty`() {
        val result = validator.validateLoginInput("footscray", "", "8079691")
        assertFalse(result.isValid)
        assertEquals("Username is required", result.usernameError)
        assertNull(result.campusError)
        assertNull(result.passwordError)
    }

    @Test
    fun `password is empty`() {
        val result = validator.validateLoginInput("footscray", "Sandra", "")
        assertFalse(result.isValid)
        assertEquals("Password is required", result.passwordError)
        assertNull(result.campusError)
        assertNull(result.usernameError)
    }

    @Test
    fun `campus is invalid`() {
        val result = validator.validateLoginInput("melbourne", "Sandra", "8079691")
        assertFalse(result.isValid)
        assertEquals("Invalid campus", result.campusError)
    }

    @Test
    fun `username is invalid`() {
        val result = validator.validateLoginInput("footscray", "San123", "8079691")
        assertFalse(result.isValid)
        assertEquals("Username must contain only letters", result.usernameError)
    }

    @Test
    fun `password is invalid`() {
        val result = validator.validateLoginInput("footscray", "Sandra", "s8079691")
        assertFalse(result.isValid)
        assertEquals("Password must be exactly 7 digits", result.passwordError)
    }

    @Test
    fun `valid campus and credentials`() {
        val result = validator.validateLoginInput("footscray", "Sandra", "8079691")
        assertTrue(result.isValid)
        assertNull(result.campusError)
        assertNull(result.usernameError)
        assertNull(result.passwordError)
    }
}
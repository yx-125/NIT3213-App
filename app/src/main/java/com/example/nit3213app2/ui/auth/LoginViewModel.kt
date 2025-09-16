package com.example.nit3213app2.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213app2.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException

/**
 * ViewModel for the login screen
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    // Selected campus, defaults to "footscray"
    var campus: String = "footscray"

    // --- UI state flows ---
    private val _loading = MutableStateFlow(false)
    // Emits true while a login request is in progress
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    // Emits an error message when login fails or null when there is no error
    val error = _error.asStateFlow()
    private val _keypass = MutableStateFlow<String?>(null)
    // Emits the keypass token when login succeeds
    val keypass = _keypass.asStateFlow()

    /**
     * Attempts to log a user in with the provided credentials
     */
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repo.login(campus, username, password)
                _keypass.value = response.keypass
            } catch (e: HttpException) {
                // Handles 4xx and 5xx errors (invalid credentials, server error)
                _error.value = "Login failed. Check campus and credentials."
            } catch (e: IOException) {
                // Handles network issues (offline, timeout)
                _error.value = "Network error. Please check your connection."
            } catch (e: Exception) {
                // Fallback for unexpected errors
                _error.value = "Something went wrong. Please try again."
            } finally {
                _loading.value = false
            }
        }
    }
}
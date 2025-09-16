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

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    var campus: String = "footscray"

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _keypass = MutableStateFlow<String?>(null)
    val keypass = _keypass.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repo.login(campus, username, password)
                _keypass.value = response.keypass
            } catch (e: HttpException) {
                // Handles 4xx and 5xx errors (wrong campus, wrong credentials, server issue)
                _error.value = "Login failed. Check campus and credentials."
            } catch (e: IOException) {
                // Handles no internet / timeout
                _error.value = "Network error. Please check your connection."
            } catch (e: Exception) {
                // Anything unexpected
                _error.value = "Something went wrong. Please try again."
            } finally {
                _loading.value = false
            }
        }
    }
}
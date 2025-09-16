package com.example.nit3213app2.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213app2.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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
            } catch (e: Exception) {
                _error.value = e.message ?: "Login failed. Check credentials & campus."
            } finally {
                _loading.value = false
            }
        }
    }
}

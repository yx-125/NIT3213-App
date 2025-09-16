package com.example.nit3213app2.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213app2.data.dashboard.DashboardRepository
import com.example.nit3213app2.data.dashboard.DashboardEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: DashboardRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<DashboardEntity>>(emptyList())
    val items: StateFlow<List<DashboardEntity>> = _items

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            try {
                _items.value = repo.fetchDashboard(keypass)
            } catch (e: Exception) {
                _error.value = "Failed to load dashboard: ${e.message ?: "Unknown error"}"
            }
        }
    }

}

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

/**
 * ViewModel for the dashboard screen
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: DashboardRepository
) : ViewModel() {

    // --- UI state flows ---
    // Emits the current list of dashboard entities
    private val _items = MutableStateFlow<List<DashboardEntity>>(emptyList())
    val items: StateFlow<List<DashboardEntity>> = _items
    // Emits an error message if dashboard loading fails, or null otherwise
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    /**
     * Loads the dashboard entities using the given keypass
     */
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

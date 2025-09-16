package com.example.nit3213app2.data.dashboard

import javax.inject.Inject

/**
 * Repository responsible for fetching dashboard-related data
 */
class DashboardRepository @Inject constructor(
    private val api: DashboardApiService
) {
    /**
     * Retrieves a list of dashboard entities from the API
     */
    suspend fun fetchDashboard(keypass: String): List<DashboardEntity> {
        return api.getDashboard(keypass).entities
    }
}
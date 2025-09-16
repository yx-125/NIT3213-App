package com.example.nit3213app2.data.dashboard

import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: DashboardApiService
) {
    suspend fun fetchDashboard(keypass: String): List<DashboardEntity> {
        return api.getDashboard(keypass).entities
    }
}

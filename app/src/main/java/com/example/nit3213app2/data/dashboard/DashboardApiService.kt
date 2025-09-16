package com.example.nit3213app2.data.dashboard

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit API definition for dashboard-related endpoint
 */
interface DashboardApiService {
    /**
     * Retrieves the dashboard entities for the given keypass.
     */
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): DashboardResponse
}
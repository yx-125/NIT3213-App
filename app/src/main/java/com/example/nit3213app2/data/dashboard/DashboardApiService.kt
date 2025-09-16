package com.example.nit3213app2.data.dashboard

import retrofit2.http.GET
import retrofit2.http.Path

interface DashboardApiService {
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): DashboardResponse
}

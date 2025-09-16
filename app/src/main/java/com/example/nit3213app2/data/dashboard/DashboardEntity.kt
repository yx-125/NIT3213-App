package com.example.nit3213app2.data.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DashboardEntity(
    val artworkTitle: String,
    val artist: String,
    val medium: String,
    val year: Int,
    val description: String?
) : Parcelable
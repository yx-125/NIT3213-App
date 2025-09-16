package com.example.nit3213app2

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

/**
 * Base application class for the app
 */
@HiltAndroidApp
class MyBaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("nit3213", "Application onCreate was called")
    }
}
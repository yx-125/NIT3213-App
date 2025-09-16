package com.example.nit3213app2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point of the application
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- Setup Navigation Host and Controller ---
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHost.navController

        // --- Setup toolbar to integrate with navigation ---
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val appBarConfig = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfig)
        // Clear default title
        toolbar.title = ""

        // Control toolbar visibility
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.detailsFragment) {
                // Show toolbar only on Details screen
                toolbar.visibility = View.VISIBLE
            } else {
                // Hide toolbar on other screens
                // Using post() ensures this runs after navigation transition,
                // avoiding flickering issues
                toolbar.post {
                    toolbar.visibility = View.GONE
                }
            }
        }

    }
}
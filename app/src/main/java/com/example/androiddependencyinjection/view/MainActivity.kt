package com.example.androiddependencyinjection.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.androiddependencyinjection.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onNavigateUp()
        navController = setupBackButtonOnToolbar(this, R.id.fragment)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun setupBackButtonOnToolbar(
        activity: AppCompatActivity,
        container: Int
    ): NavController {
        val navController = Navigation.findNavController(activity, container)
        NavigationUI.setupActionBarWithNavController(activity, navController)
        return navController
    }
}

package com.moises.resume.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moises.resume.R
import com.moises.resume.core.setToolbar
import kotlinx.android.synthetic.main.appbar_toolbar.*

class BottomMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_menu)
        val navView: BottomNavigationView = this.findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_resume, R.id.navigation_skills
            )
        )
        this.setToolbar(toolbar, toolbar_title, getString(R.string.title_resume), false)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener { menuItem ->
            onNavDestinationSelected(menuItem, navController)
            when (menuItem.itemId) {
                R.id.navigation_resume -> {
                    toolbar_title.text = getString(R.string.title_resume)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_skills -> {
                    toolbar_title.text = getString(R.string.title_skills)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}

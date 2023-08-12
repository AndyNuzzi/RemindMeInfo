package com.example.remindmeinfo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.remindmeinfo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // show icon in action bar
        supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                //R.id.navigation_home, R.id.navigation_notifications,
                R.id.navigation_reminder, R.id.navigation_map
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    fun onNavigationItemSelected(item: MenuItem):Boolean{

        when (item.itemId){
            R.id.navigation_help -> startHelp()
            R.id.navigation_setting -> startSetting()
            R.id.navigation_home -> startMain()
            R.id.navigation_profile -> startProfile()
        }
        return true
    }

    fun startHelp() {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

    fun startSetting() {
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }

    fun startMain(){
        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }

    fun startProfile(){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }


}
package com.example.remindmeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu_setting, menu)
        return true
    }

    fun onNavigationItemSelected(item: MenuItem):Boolean{

        when (item.itemId){
            R.id.navigation_help -> startHelp()
            R.id.navigation_home -> startMain()
            R.id.navigation_profile -> startProfile()
        }
        return true
    }

    fun startHelp() {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }
    fun startMain(){
        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }

    fun startProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}
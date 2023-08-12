package com.example.remindmeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.remindmeinfo.ui.profile.ProfileFragment

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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
            R.id.navigation_setting -> startSetting()
            R.id.navigation_help -> startHelp()
            R.id.navigation_home -> startMain()
        }
        return true
    }

    fun startSetting() {
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }

    fun startHelp() {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }
    fun startMain(){
        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }

}
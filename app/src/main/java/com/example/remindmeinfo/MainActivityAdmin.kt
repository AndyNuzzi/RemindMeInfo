package com.example.remindmeinfo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.remindmeinfo.databinding.ActivityMainBinding
import com.example.remindmeinfo.ui.help.HelpFragment
import com.example.remindmeinfo.ui.map_admin.MapAdminFragment
import com.example.remindmeinfo.ui.profile.ProfileFragment
import com.example.remindmeinfo.ui.reminder_admin.ReminderAdminFragment
import com.example.remindmeinfo.ui.setting.SettingFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivityAdmin : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        fragmentManager = supportFragmentManager

        //navigation drawer
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, 0, 0)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)


        //botom navigation
        binding.botomNavigation.background = null

        binding.botomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_reminder -> openFragment(ReminderAdminFragment())
                R.id.navigation_map -> openFragment(MapAdminFragment())
            }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(ReminderAdminFragment())

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Adding new reminder", Toast.LENGTH_SHORT).show()
        }


        // show icon in action bar
        supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onNavigationItemSelected(item: MenuItem):Boolean{

        when (item.itemId){
            R.id.navigation_help -> openFragment(HelpFragment())
            R.id.navigation_setting -> openFragment(SettingFragment())
            R.id.navigation_home -> startMain()
            R.id.navigation_profile -> openFragment(ProfileFragment())
            R.id.navigation_session -> singOutAdmin()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)

    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction =  fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        //fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun startMain(){
        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }

    fun singOutAdmin(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }

}
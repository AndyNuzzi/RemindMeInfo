package com.example.remindmeinfo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.remindmeinfo.databinding.ActivityMainBinding
import com.example.remindmeinfo.ui.help.HelpFragment
import com.example.remindmeinfo.ui.map_admin.MapsAdminFragment
import com.example.remindmeinfo.ui.profile.ProfileAdminFragment
import com.example.remindmeinfo.ui.profile.ProfileUserFromFragment
import com.example.remindmeinfo.ui.reminder_admin.AddingReminderActivity
import com.example.remindmeinfo.ui.reminder_admin.ItemReminderAdminFragment
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
                R.id.navigation_reminder -> openFragment(ItemReminderAdminFragment())
                R.id.navigation_map -> openFragment(MapsAdminFragment())
            }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(ItemReminderAdminFragment())

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddingReminderActivity::class.java)
            startActivity(intent)
        }


        // show icon in action bar
        supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu_admin, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profileAdmin -> openFragment(ProfileAdminFragment())
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem):Boolean{

        when (item.itemId){
            R.id.navigation_help -> openFragment(HelpFragment())
            R.id.navigation_setting -> openFragment(SettingFragment())
            R.id.navigation_home -> startMain()
            R.id.navigation_profile -> openFragment(ProfileAdminFragment())
            R.id.navigation_profile_user -> openFragment(ProfileUserFromFragment())
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
package com.example.remindmeinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentManager
import com.example.remindmeinfo.databinding.ActivityMainUserBinding
import com.example.remindmeinfo.ui.map_admin.MapAdminFragment
import com.example.remindmeinfo.ui.reminder_admin.ReminderAdminFragment
import com.google.android.material.navigation.NavigationView
import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.remindmeinfo.ui.calendar_user.CalendarUserFragment
import com.example.remindmeinfo.ui.help.HelpFragment
import com.example.remindmeinfo.ui.medical_info_user.MedicalInfoUserFragment
import com.example.remindmeinfo.ui.panic_user.PanicUserFragment
import com.example.remindmeinfo.ui.pharmacy_user.PharmacyInfoUserFragment
import com.example.remindmeinfo.ui.pharmacy_user.PharmacyInfoUserViewModel
import com.example.remindmeinfo.ui.profile.ProfileFragment
import com.example.remindmeinfo.ui.reminder_list_user.ReminderListUserFragment
import com.example.remindmeinfo.ui.reminder_list_user.ReminderListUserViewModel
import com.example.remindmeinfo.ui.setting.SettingFragment
import com.google.firebase.auth.FirebaseAuth


class MainActivityUser : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

     private lateinit var fragmentManager: FragmentManager
     private lateinit var binding: ActivityMainUserBinding

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         binding = ActivityMainUserBinding.inflate(layoutInflater)
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
                 R.id.navigation_reminder_list -> openFragment(ReminderListUserFragment())
                 R.id.navigation_calendar -> openFragment(CalendarUserFragment())
                 R.id.navigation_panic -> openFragment(PanicUserFragment())
                 R.id.navigation_medical_info -> openFragment(MedicalInfoUserFragment())
                 R.id.navigation_pharmacy_info -> openFragment(PharmacyInfoUserFragment())
             }
             true
         }

         fragmentManager = supportFragmentManager
         openFragment(ReminderListUserFragment())


         // show icon in action bar
         supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
         supportActionBar!!.setDisplayShowHomeEnabled(true)
     }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
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
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.getOnBackPressedDispatcher().onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        //fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun startMain() {
        val intent = Intent(this, MainActivityUser::class.java)
        startActivity(intent)
    }

    fun singOutAdmin(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }

}
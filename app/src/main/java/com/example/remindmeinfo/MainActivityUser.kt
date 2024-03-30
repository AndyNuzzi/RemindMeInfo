package com.example.remindmeinfo

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentManager
import com.example.remindmeinfo.databinding.ActivityMainUserBinding
import com.google.android.material.navigation.NavigationView
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.remindmeinfo.ui.calendar_user.ItemCalendarUserFragment
import com.example.remindmeinfo.ui.calendar_user.placeholder.PlaceholderContent.currentUser
import com.example.remindmeinfo.ui.help.HelpFragment
import com.example.remindmeinfo.ui.medical_info_user.ItemMedicalPDFFragment
import com.example.remindmeinfo.ui.pharmacy_user.ItemFragment
import com.example.remindmeinfo.ui.profile.ProfileUserFragment
import com.example.remindmeinfo.ui.reminder_list_user.UserReminderFragment
import com.example.remindmeinfo.ui.setting.SettingFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivityUser : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

     private lateinit var fragmentManager: FragmentManager
     private lateinit var binding: ActivityMainUserBinding

    private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         //maps
         fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
         solicitarPermisosUbicacion()

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
                 R.id.navigation_reminder_list -> openFragment(UserReminderFragment())
                 R.id.navigation_calendar -> openFragment(ItemCalendarUserFragment())
                 R.id.navigation_medical_info -> openFragment(ItemMedicalPDFFragment())
                 R.id.navigation_pharmacy_info -> openFragment(ItemFragment())
             }
             true
         }

         fragmentManager = supportFragmentManager
         openFragment(UserReminderFragment())


         // show icon in action bar
         //supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
         supportActionBar!!.setDisplayShowHomeEnabled(true)
     }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_vital_control, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.vital_control -> startVital()
            R.id.panic_button -> startPanic()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_help -> openFragment(HelpFragment())
            R.id.navigation_setting -> openFragment(SettingFragment())
            R.id.navigation_home -> startMain()
            R.id.navigation_profile_user -> openFragment(ProfileUserFragment())
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

    fun startVital(){
        val intent = Intent(this, VitalControlActivity::class.java)
        startActivity(intent)
    }

    fun startPanic(){
        val intent = Intent(this, PanicUserActivity::class.java)
        startActivity(intent)
    }

    private fun solicitarPermisosUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        } else {
            obtenerUbicacion()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacion()
            } else {
                // El usuario no concedió los permisos. Puedes mostrar un mensaje explicativo aquí.
            }
        }
    }

    private fun obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitud = it.latitude
                    val longitud = it.longitude

                    var email = currentUser?.email.toString()
                    val db = FirebaseFirestore.getInstance()

                    db.collection("users").document(email).update("latitud", latitud)
                    db.collection("users").document(email).update("longitud", longitud)

                }
            }
        }
    }



}

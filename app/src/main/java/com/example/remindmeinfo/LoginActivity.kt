package com.example.remindmeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

import kotlin.properties.Delegates

class LoginActivity : AppCompatActivity() {

    companion object{
        lateinit var useremail: String
        lateinit var providerSession: String
    }

    private var email by Delegates.notNull<String>()
    private var psswrd by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etPsswrd: EditText
    private lateinit var lyTerms: LinearLayout

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lyTerms = findViewById(R.id.legalTerms)
        lyTerms.visibility = View.INVISIBLE

        etEmail = findViewById(R.id.textEmail)
        etPsswrd = findViewById(R.id.textPassword)
        mAuth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser!= null){
            goHome(currentUser.email.toString(), currentUser.providerId)
        }

    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    fun login(view:View) {
        email = etEmail.text.toString()
        psswrd = etPsswrd.text.toString()

        mAuth.signInWithEmailAndPassword(email, psswrd)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful) goHome(email, "google")
                else{
                    if (lyTerms.visibility == View.INVISIBLE){
                        lyTerms.visibility = View.VISIBLE
                    }
                    else{
                        var cbAcept = findViewById<CheckBox>(R.id.check)
                        if (cbAcept.isChecked) register()
                    }
                }
            }
    }

    private fun goHome(email: String, provider: String){
        useremail = email
        providerSession = provider

        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }

    private fun register(){
        etEmail = findViewById(R.id.textEmail)
        etPsswrd = findViewById(R.id.textPassword)

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, psswrd)
            .addOnCompleteListener {
                if(it.isSuccessful){

                    var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
                    var dbRegister = FirebaseFirestore.getInstance()
                    dbRegister.collection("users").document(email).set(hashMapOf(
                        "user" to email,
                        "dateRegister" to dateRegister
                    ))
                    goHome(email, "google")

                   // val intent = Intent(this, RegisterActivity::class.java)
                   // startActivity(intent)
                }
                else Toast.makeText(this, "Error, algo no ha salido como se esperaba", Toast.LENGTH_SHORT).show()
            }
    }

    fun forgottenPsswrd(view: View){
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    fun goTerms(view: View){
        startActivity(Intent(this, TermsActivity::class.java))
    }

}
package com.example.remindmeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth

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

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.textEmail)
        etPsswrd = findViewById(R.id.textPassword)
        mAuth = FirebaseAuth.getInstance()

       // manageButtonLogin()
       // etEmail.doOnTextChanged { text, start, before, count -> manageButtonLogin() }
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
                goHome(email, "google")
            }
    }

    private fun goHome(email: String, provider: String){
        useremail = email
        providerSession = provider

        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }

    fun forgottenPsswrd(view: View){
        //startActivity(Intent(this, ForgotPasswordActivity::class.java))
        var em = etEmail.text.toString()

        if(!TextUtils.isEmpty(em)){
            mAuth.sendPasswordResetEmail(em)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Email enviado a $em", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "No se ha encontrado usuario con ese correo", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun manageButtonLogin(){
        var tvLogin = findViewById<TextView>(R.id.buttonLogin)

        email = etEmail.text.toString()
        psswrd = etPsswrd.text.toString()

        if(TextUtils.isEmpty(psswrd) || ValidateEmail.isEmail(email) == false){
            tvLogin.setBackgroundColor((ContextCompat.getColor(this, R.color.gray)))
            tvLogin.isEnabled = false
        } else{
            tvLogin.setBackgroundColor((ContextCompat.getColor(this, R.color.blue)))
            tvLogin.isEnabled = true
        }
    }

    fun signIn_Act(view: View){
        startActivity(Intent(this, SignInActivity::class.java))
    }

}
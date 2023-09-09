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
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

import kotlin.properties.Delegates

class SignInActivity : AppCompatActivity() {

    companion object{
        lateinit var useremailS: String
        lateinit var providerSession: String
    }

    private var emailS by Delegates.notNull<String>()
    private var psswrdS by Delegates.notNull<String>()
    private lateinit var etEmailS: EditText
    private lateinit var etPsswrdS: EditText

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        etEmailS = findViewById(R.id.textEmailS)
        etPsswrdS = findViewById(R.id.textPasswordS)
        mAuth = FirebaseAuth.getInstance()

      //  manageButtonSignIn()
      //  etEmailS.doOnTextChanged { text, start, before, count -> manageButtonSignIn() }
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

    private fun goHome(emailS: String, provider: String){
        useremailS = emailS
        providerSession = provider

        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun register(view:View){
        emailS = etEmailS.text.toString()
        psswrdS = etPsswrdS.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailS, psswrdS)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    var cbAcept = findViewById<CheckBox>(R.id.check)

                    if(cbAcept.isChecked){
                        var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
                        var dbRegister = FirebaseFirestore.getInstance()
                        dbRegister.collection("users").document(emailS).set(hashMapOf(
                            "user" to emailS,
                            "dateRegister" to dateRegister
                        ))
                        goHome(emailS, "google")
                    } else if(!cbAcept.isChecked){
                        Toast.makeText(this, "Tiene que marcar el checkbox de terminos de uso", Toast.LENGTH_SHORT).show()
                    }
                }
                else Toast.makeText(this, "Error, algo no ha salido como se esperaba", Toast.LENGTH_SHORT).show()
            }
    }

    fun goTerms(view: View){
        startActivity(Intent(this, TermsActivity::class.java))
    }

    private fun manageButtonSignIn(){
        val tvSignIn = findViewById<TextView>(R.id.buttonSignIn)

        emailS = etEmailS.text.toString()
        psswrdS = etPsswrdS.text.toString()


        if(TextUtils.isEmpty(psswrdS) || !ValidateEmail.isEmail(emailS)){
            tvSignIn.setBackgroundColor((ContextCompat.getColor(this, R.color.gray)))
            tvSignIn.isEnabled = false
        } else{
            tvSignIn.setBackgroundColor((ContextCompat.getColor(this, R.color.blue)))
            tvSignIn.isEnabled = true
        }
    }


    fun signInGoogle(view:View){

    }

}